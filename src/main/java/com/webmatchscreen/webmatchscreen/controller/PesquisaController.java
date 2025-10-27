package com.webmatchscreen.webmatchscreen.controller;

import com.webmatchscreen.webmatchscreen.model.Titulo;
import com.webmatchscreen.webmatchscreen.record.TituloRecord;
import com.webmatchscreen.webmatchscreen.service.ApiServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pesquisa")
public class PesquisaController {

    private final ApiServices apiServices;

    public PesquisaController(ApiServices apiServices){
        this.apiServices = apiServices;
    }

    @GetMapping("/{titulo}")
    public Titulo buscarPorTitulo(@PathVariable String titulo) {
        return apiServices.buscarTitulo(titulo);
    }


}
