package com.github.vdemeester.macchiato.compose;

import com.github.vdemeester.macchiato.compose.infrastrcuture.yml.YmlFileManager;
import com.github.vdemeester.macchiato.compose.infrastrcuture.yml.docker.ContainerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Project {

    final static Logger logger = LoggerFactory.getLogger(Project.class);
    private final YmlFileManager ymlFileManager = YmlFileManager.INSTANCE;
    private final ContainerManager containerManager = ContainerManager.INSTANCE;
    private String projectName;
    private Map<String, Object> projectComposeConfig = null;
    private List<String> containerIds = new ArrayList<>();

    public Project(String projectName, String composeFile) {
        this.projectName = projectName;
        projectComposeConfig = ymlFileManager.extractComposeConfiguration(composeFile);
    }

    public List<String> create() {
        projectComposeConfig.keySet()
                .stream()
                .forEach(containerName -> {
                    Map<String, Object> containerConfig = (Map<String, Object>) projectComposeConfig.get(containerName);
                    containerIds.add(containerManager.createContainer(projectName + "_" + containerName + "_1", containerConfig));
                });
        logger.info("containers created {0}", containerIds);
        return containerIds;
    }
}