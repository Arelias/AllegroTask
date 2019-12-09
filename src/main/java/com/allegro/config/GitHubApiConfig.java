package com.allegro.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class GitHubApiConfig {
    @Value("${git.api.endpoint}")
    private String gitApiEndpoint;

    @Value("${git.api.token}")
    private String gitApiToken;
}
