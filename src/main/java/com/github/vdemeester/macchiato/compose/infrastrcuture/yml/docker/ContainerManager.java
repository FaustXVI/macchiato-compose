package com.github.vdemeester.macchiato.compose.infrastrcuture.yml.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.command.PullImageResultCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public enum ContainerManager {
    INSTANCE;

    final static Logger LOGGER = LoggerFactory.getLogger(ContainerManager.class);


    private String createContainer(String name, String imageLabel, String commandToExecute) {

        DockerClient dockerClient = buildDockerClient();
        pullImageIfNotExists(dockerClient, imageLabel);

        CreateContainerResponse container = dockerClient.createContainerCmd(imageLabel)
                .withName(name)
                .withCmd(commandToExecute)
                .exec();

        LOGGER.info("Created container {}", container.getId());
        return container.getId();
    }

    private void pullImageIfNotExists(DockerClient dockerClient, String imageLabel) {
        try {
            dockerClient.inspectImageCmd(imageLabel).exec();
        } catch (NotFoundException e) {
            LOGGER.info("Pulling image '"+imageLabel+"'");
            dockerClient.pullImageCmd(imageLabel).withTag("latest").exec(new PullImageResultCallback()).awaitSuccess();
        }
    }

    private DockerClient buildDockerClient() {
        DockerClientConfig config = DockerClientConfig.createDefaultConfigBuilder()
                .build();

        return DockerClientBuilder.getInstance(config)
                .withDockerCmdExecFactory(DockerClientBuilder.getDefaultDockerCmdExecFactory())
                .build();
    }

    public String createContainer(String containerName, Map<String, Object> containerConfig) {
        return createContainer(containerName, (String) containerConfig.get("image"), (String) containerConfig.get("command"));
    }
}