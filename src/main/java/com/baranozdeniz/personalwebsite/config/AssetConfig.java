package com.baranozdeniz.personalwebsite.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AssetConfig {

    private final String serverFilePath = System.getProperty("user.dir") + "/assets/";

    @PostConstruct
    public void init() {
        File directory = new File(serverFilePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Assets directory created: " + serverFilePath);
            } else {
                System.err.println("Failed to create assets directory: " + serverFilePath);
            }
        } else {
            System.out.println("Assets directory already exists: " + serverFilePath);
        }
    }

}
