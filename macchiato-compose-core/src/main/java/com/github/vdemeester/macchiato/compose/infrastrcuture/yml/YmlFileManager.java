package com.github.vdemeester.macchiato.compose.infrastrcuture.yml;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public enum YmlFileManager {
    INSTANCE;

    public Map<String, Object> extractComposeConfiguration(String composeFile) {
        Yaml yaml = new Yaml();
        try {
            InputStream input = new FileInputStream(new File(composeFile));
            return (Map<String, Object>) yaml.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}