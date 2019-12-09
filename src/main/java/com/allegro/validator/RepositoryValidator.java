package com.allegro.validator;

import com.allegro.domain.GitApiRepoResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RepositoryValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryValidator.class);

    public GitApiRepoResponseDto validateRepository(final GitApiRepoResponseDto repository){
        LOGGER.info("Verifying repository data.");
        final GitApiRepoResponseDto output ;
        try{
            repository.getDescription().isEmpty();
        } catch (Exception e){
            output = new GitApiRepoResponseDto(
                    repository.getFullName(),
                    "",
                    repository.getCloneUrl(),
                    repository.getStars(),
                    repository.getCreatedAt()
            );
            LOGGER.info("Data verified, null removed.");
            return output;
        }
        LOGGER.info("Data verified.");
        return repository;
    }
}
