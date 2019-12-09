package com.allegro.mapper;

import com.allegro.domain.GitApiRepoResponseDto;
import com.allegro.domain.TaskApiRepoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper {

    public TaskApiRepoResponseDto mapToTaskApiRepo(GitApiRepoResponseDto repository){
        return new TaskApiRepoResponseDto(
                repository.getFullName(),
                repository.getDescription(),
                repository.getCloneUrl(),
                repository.getStars(),
                repository.getCreatedAt()
        );
    }

}
