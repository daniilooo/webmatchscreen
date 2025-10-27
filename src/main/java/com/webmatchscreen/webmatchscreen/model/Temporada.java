package com.webmatchscreen.webmatchscreen.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Temporada {
    @Id
    private Long idTemporada;
    private int numeroTemporada;
//    private int quantidadeEpisodios;

    @ManyToOne
    @JoinColumn(name = "titulo_id", nullable = false)
    @ToString.Exclude
    private Titulo titulo;
}
