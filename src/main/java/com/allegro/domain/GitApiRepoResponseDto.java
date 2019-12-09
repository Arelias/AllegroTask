package com.allegro.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitApiRepoResponseDto {

    @JsonAlias({"full_name"})
    String fullName;
    @JsonAlias({"description"})
    String description;
    @JsonAlias({"clone_url"})
    String cloneUrl;
    @JsonAlias({"stargazers_count"})
    Long stars;
    @JsonAlias({"created_at"})
    LocalDateTime createdAt;

}

