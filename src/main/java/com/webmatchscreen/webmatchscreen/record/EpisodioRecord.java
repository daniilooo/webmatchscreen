package com.webmatchscreen.webmatchscreen.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodioRecord(@JsonAlias("Title") String titulo,
                             @JsonAlias("Released") String lancamento,
                             @JsonAlias("Episode") int numeroEpisodio,
                             @JsonAlias("Runtime") String duracao,
                             @JsonAlias("Plot") String sinopse,
                             @JsonAlias("Poster") String poster) {
}
