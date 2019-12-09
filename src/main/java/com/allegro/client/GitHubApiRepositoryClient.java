package com.allegro.client;

import com.allegro.config.GitHubApiConfig;
import com.allegro.domain.GitApiRepoResponseDto;
import com.allegro.validator.RepositoryValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GitHubApiRepositoryClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GitHubApiRepositoryClient.class);

    private final GitHubApiConfig gitHubApiConfig;

    private final RestTemplate restTemplate;

    public GitHubApiRepositoryClient(
            GitHubApiConfig gitHubApiConfig,
            RestTemplateBuilder restTemplateBuilder) {
        this.gitHubApiConfig = gitHubApiConfig;
        this.restTemplate = restTemplateBuilder.build();
    }

    public GitApiRepoResponseDto getRestRepo(String owner, String repositoryName) throws Exception {
        LOGGER.info("Looking up repository: " + owner + "/" + repositoryName);
        URI url = urlBuilder(owner, repositoryName);
        final GitApiRepoResponseDto response;
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    authTokenHeader(),
                    GitApiRepoResponseDto.class)
                    .getBody();
            LOGGER.info("Looking up done.");
            return response;
        } catch (Exception e) {
            LOGGER.info("Exception encountered");
            LOGGER.info("Http method: GET, method name: getRestRepo");
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    private URI urlBuilder(String owner, String repositoryName) {
        return UriComponentsBuilder.fromHttpUrl(
                gitHubApiConfig
                .getGitApiEndpoint() + "/repos/" + owner + "/" + repositoryName)
                .build()
                .encode()
                .toUri();
    }

    private HttpEntity<String> authTokenHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + gitHubApiConfig.getGitApiToken());
        return new HttpEntity<>(httpHeaders);
    }
}
