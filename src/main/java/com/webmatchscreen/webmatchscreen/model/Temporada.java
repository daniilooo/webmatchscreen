package com.webmatchscreen.webmatchscreen.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Temporada {

    public Temporada (String tituloTemporada, int numeroTemporada){
        this.tituloTemporada = tituloTemporada;
        this.numeroTemporada = numeroTemporada;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTemporada;
    private int numeroTemporada;

    private String tituloTemporada;

    @ManyToOne
    @JoinColumn(name = "idTitulo")
    private Titulo titulo;
    private int quantidadeEpisodios;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> listaEpisodios = new ArrayList<>();
}
