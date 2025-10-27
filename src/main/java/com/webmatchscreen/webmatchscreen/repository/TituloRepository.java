package com.webmatchscreen.webmatchscreen.repository;

import com.webmatchscreen.webmatchscreen.ENUM.TipoTitulo;
import com.webmatchscreen.webmatchscreen.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long> {

    List<Titulo> findByTituloContainingIgnoreCase(String titulo);

    List<Titulo> findByTipo(TipoTitulo tipo);

    List<Titulo> findByTipoAndGeneroContainingIgnoreCase(TipoTitulo tipo, String genero);

    List<Titulo> findByGeneroIgnoreCase(String genero);

}
