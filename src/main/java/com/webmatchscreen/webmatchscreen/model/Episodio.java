package com.webmatchscreen.webmatchscreen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Episodio {

    public Episodio(String titulo, String lancamento, int episodio){
        this.titulo = titulo;
        this.lancamento = lancamento;
        this.episodio = episodio;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEpisodio;
    private String titulo;
    private String lancamento;
    private int episodio;

    @ManyToOne
    @JoinColumn(name = "idTemporada")
    private Temporada temporada;

}
