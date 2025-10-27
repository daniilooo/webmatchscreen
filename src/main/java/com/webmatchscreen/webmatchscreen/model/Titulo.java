package com.webmatchscreen.webmatchscreen.model;

import com.webmatchscreen.webmatchscreen.ENUM.TipoTitulo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Titulo {

    public Titulo (String titulo, String lancamento, String duracao, String genero, String sinopse, String poster){
        this.setTitulo(titulo);
        this.setLancamento(lancamento);
        this.setDuracao(duracao);
        this.setGenero(genero);
        this.setSinopse(sinopse);
        this.setPoster(poster);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTitulo;

    private String titulo;
    private String lancamento;
    private String duracao;
    private String genero;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String sinopse;

    private String poster;

    @Enumerated(EnumType.STRING)
    private TipoTitulo tipo;

    @OneToMany(mappedBy = "titulo", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Temporada> temporadas = new ArrayList<>();

}
