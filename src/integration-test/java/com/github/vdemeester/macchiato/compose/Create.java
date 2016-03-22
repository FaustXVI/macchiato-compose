package com.github.vdemeester.macchiato.compose;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

public class Create {

    private DockerClient client;

    @Before
    public void setUp() {
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                                                      .build();
        client = DockerClientBuilder.getInstance(config)
                                    .build();
    }

    @Test
    public void shouldCreateDockerContainersFromComposeFile() {
        Project project = new Project("myproject", "docker-compose.yml");
        List<String> containerIds = project.create();

        List<?> containers = containerIds.stream()
                                         .map(this::getContainer)
                                         .collect(Collectors.toList());

        assertThat(containers).hasSize(1)
                              .extracting("Name", "State.Running", "Config.Image", "Config.Cmd")
                              .contains(tuple("myproject_busybox_1", true, "busybox", singletonList("top")));
    }

    private InspectContainerCmd getContainer(String containerId) {
        // TODO Call API docker O:)
        return client.inspectContainerCmd(containerId);
    }


}