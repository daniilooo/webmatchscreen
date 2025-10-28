package com.webmatchscreen.webmatchscreen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webmatchscreen.webmatchscreen.ENUM.TipoTitulo;
import com.webmatchscreen.webmatchscreen.interfaces.ConversorDados;
import com.webmatchscreen.webmatchscreen.model.Episodio;
import com.webmatchscreen.webmatchscreen.model.Temporada;
import com.webmatchscreen.webmatchscreen.model.Titulo;
import com.webmatchscreen.webmatchscreen.record.EpisodioRecord;
import com.webmatchscreen.webmatchscreen.record.TemporadaRecord;
import com.webmatchscreen.webmatchscreen.record.TituloRecord;
import com.webmatchscreen.webmatchscreen.repository.TituloRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ApiServices {
    private final String apiUrl;
    private final String apiKey;

    private final TituloRepository tituloRepository;

    public ApiServices(Dotenv dotenv, TituloRepository tituloRepository) {
        this.apiUrl = dotenv.get("API_URL") + "?t=";
        this.apiKey = "&plot=full&apikey=" + dotenv.get("API_KEY");
        this.tituloRepository = tituloRepository;
    }

    private String montarUrl(Map<String, String> parametrosBusca) {

        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(apiUrl);

        for (Map.Entry<String, String> entry : parametrosBusca.entrySet()) {
            String chave = entry.getKey();
            String valor = entry.getValue();

            if (valor == null || valor.isBlank()) {
                continue;
            }

            if (chave.equalsIgnoreCase("titulo")) {
                sbUrl.append(valor.replace(" ", "+"));
            }

            try {
                if (chave.equalsIgnoreCase("season")) {
                    int temporada = Integer.parseInt(valor);
                    sbUrl.append("&season=" + temporada);
                }

                if (chave.equalsIgnoreCase("episode")) {
                    int episodio = Integer.parseInt(valor);
                    sbUrl.append("&episode=" + episodio);
                }

            } catch (NumberFormatException e) {
                System.out.println("Parametros de busca de temporada ou episódio inválidos");
            }
        }

        sbUrl.append(apiKey);

        return sbUrl.toString();
    }

    private <T> T consultarApi(Map<String, String> parametros, Class<T> classe) {
        try {
            String url = montarUrl(parametros);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(body, classe);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    @Cacheable(value = "titulos", key = "#titulo")
    public <T> T pesquisar(Map<String, String> parametros, Class<T> classe) {

        if (parametros == null || parametros.isEmpty()) {
            throw new IllegalArgumentException("Parâmetros de busca não podem ser nulos ou vazios");
        }

        return this.consultarApi(parametros, classe);
    }

}

