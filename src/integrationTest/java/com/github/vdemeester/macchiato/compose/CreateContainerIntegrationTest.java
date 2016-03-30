package com.github.vdemeester.macchiato.compose;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class CreateContainerIntegrationTest {

    private DockerClient client;

    private List<String> containersIds = new ArrayList<>();

    @Before
    public void setUp() {
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .build();
        client = DockerClientBuilder.getInstance(config)
                .build();
    }

    @After
    public void tearsDown() {
        containersIds.stream()
                .forEach(containerId -> {
                    InspectContainerResponse containerResponse = getContainer(containerId);
                    if(containerResponse.getState().getRunning()) {
                        client.stopContainerCmd(containerId)
                                .withTimeout(2)
                                .exec();
                    }
                    client.removeContainerCmd(containerId)
                            .exec();
                });
    }

    @Test
    public void shouldCreateDockerContainersFromComposeFile() {
        Project project = new Project("myproject", "src/integrationTest/resources/compose-files/docker-compose.yml");
        containersIds = project.create();

        List<InspectContainerResponse> containers = containersIds.stream()
                .map(this::getContainer)
                .collect(toList());

        assertThat(containers).hasSize(1)
                .extracting("name", "state.running", "config.image", "config.cmd")
                .contains(tuple("/myproject_first_1", false, "busybox", new String[]{"top"}));
    }

    private InspectContainerResponse getContainer(String containerId) {
        return client.inspectContainerCmd(containerId)
                .exec();
    }
}