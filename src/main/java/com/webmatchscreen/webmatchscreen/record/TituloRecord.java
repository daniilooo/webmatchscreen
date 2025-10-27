package com.webmatchscreen.webmatchscreen.record;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TituloRecord(@JsonAlias("Title") String titulo,
                           @JsonAlias("Released") String anoLancamento,
                           @JsonAlias("Runtime") String duracao,
                           @JsonAlias("Genre") String genero,
                           @JsonAlias("Plot") String sinopse,
                           @JsonAlias("Poster") String poster,
                           @JsonAlias("Type") String tipo,
                           @JsonAlias("totalSeasons") String temporadas) {
}
