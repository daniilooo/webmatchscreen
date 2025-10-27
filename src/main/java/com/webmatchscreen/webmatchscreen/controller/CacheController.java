package com.webmatchscreen.webmatchscreen.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cache")
@RestController
public class CacheController {

    @CacheEvict(value = "titulos", allEntries = true)
    @GetMapping("/limparcache")
    public String limparCache(){
        return "Cache de títulos limpo!";
    }
}
