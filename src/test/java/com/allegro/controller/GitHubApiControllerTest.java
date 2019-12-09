package com.allegro.controller;

import com.allegro.domain.TaskApiRepoResponseDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GitHubApiControllerTest {

    @Autowired
    private GitHubApiController gitHubApiController;

    @Test
    public void shouldReturnRepository() throws Exception {
        //Given
        LocalDateTime repoDate = LocalDateTime.parse("2019-08-19T11:21:10");
        //When
        TaskApiRepoResponseDto obj = gitHubApiController.restRepo("Arelias", "REST-API");
        //Then
        Assert.assertEquals("Arelias/REST-API", obj.getFullName());
        Assert.assertEquals("Trello Api", obj.getDescription());
        Assert.assertEquals("https://github.com/Arelias/REST-API.git", obj.getCloneUrl());
        Assert.assertEquals(0L, (long)obj.getStars(), 5L);
        Assert.assertEquals(repoDate, obj.getCreatedAt());
    }
    @Test(expected = HttpClientErrorException.class)
    public void shouldReturnExceptionJson() throws Exception {
        //When & Then
        gitHubApiController.restRepo("Arelias", "NonExistentRepo");
    }
}
