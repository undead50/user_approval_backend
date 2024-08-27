package com.ebl.userapproval.config;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "apims-config")
@Getter
@Setter
public class ApimsConfig {

    private String api;

    private String username;

    private String password;

    private String certFilePath;

}
