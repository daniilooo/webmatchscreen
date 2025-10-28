package com.webmatchscreen.webmatchscreen.controller;

import com.webmatchscreen.webmatchscreen.model.Titulo;
import com.webmatchscreen.webmatchscreen.record.TemporadaRecord;
import com.webmatchscreen.webmatchscreen.record.TituloRecord;
import com.webmatchscreen.webmatchscreen.service.ApiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    private final ApiServices apiServices;

    public PesquisaController(ApiServices apiServices){
        this.apiServices = apiServices;
    }

    @GetMapping("/{titulo}")
    public TituloRecord buscarPorTitulo(@PathVariable String titulo) {

        Map<String, String> parametros = new HashMap<>();
        parametros.put("titulo", titulo);

        return apiServices.pesquisar(parametros, TituloRecord.class);
    }

    @GetMapping("/{titulo}/{temporada}")
    public TemporadaRecord buscarPortemporada(@PathVariable String titulo,
                                              @PathVariable String temporada){
        Map<String, String> parametros = new HashMap<>();
        parametros.put("titulo", titulo);
        parametros.put("temporada", temporada);

        return apiServices.pesquisar(parametros, TemporadaRecord.class);
    }



}
