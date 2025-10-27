package com.webmatchscreen.webmatchscreen.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TemporadaRecord(@JsonAlias("Title") String titulo,
                              @JsonAlias("Season") int numeroTemporada,
                              @JsonAlias("Episodes")List<EpisodioRecord> listaEpisodios) {
}
