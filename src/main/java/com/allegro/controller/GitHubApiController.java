package com.allegro.controller;

import com.allegro.client.GitHubApiRepositoryClient;
import com.allegro.domain.GitApiRepoResponseDto;
import com.allegro.domain.TaskApiRepoResponseDto;
import com.allegro.mapper.RepositoryMapper;
import com.allegro.validator.RepositoryValidator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repositories")
public class GitHubApiController {

    private final GitHubApiRepositoryClient gitHubApiRepositoryClient;

    private final RepositoryValidator repositoryValidator;

    private final RepositoryMapper repositoryMapper;

    public GitHubApiController(
            GitHubApiRepositoryClient gitHubApiRepositoryClient,
            RepositoryValidator repositoryValidator,
            RepositoryMapper repositoryMapper) {
        this.gitHubApiRepositoryClient = gitHubApiRepositoryClient;
        this.repositoryValidator = repositoryValidator;
        this.repositoryMapper = repositoryMapper;
    }

    @RequestMapping("/{owner}/{repositoryName}")
    public TaskApiRepoResponseDto restRepo(@PathVariable String owner, @PathVariable String repositoryName) throws Exception {
        GitApiRepoResponseDto output = gitHubApiRepositoryClient
                .getRestRepo(owner, repositoryName);
        return repositoryMapper.mapToTaskApiRepo(
                repositoryValidator.validateRepository(output));
    }

}
