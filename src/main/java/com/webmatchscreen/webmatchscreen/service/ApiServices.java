package com.webmatchscreen.webmatchscreen.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webmatchscreen.webmatchscreen.ENUM.TipoTitulo;
import com.webmatchscreen.webmatchscreen.interfaces.ConversorDados;
import com.webmatchscreen.webmatchscreen.model.Titulo;
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
import java.util.Optional;

@Service
public class ApiServices {
    private final String apiUrl;
    private final String apiKey;

    private final TituloRepository tituloRepository;

    public ApiServices(Dotenv dotenv, TituloRepository tituloRepository){
        this.apiUrl = dotenv.get("API_URL") + "?t=";
        this.apiKey = "&plot=full&apikey=" + dotenv.get("API_KEY");
        this.tituloRepository = tituloRepository;
    }

    private String montarUrl(String titulo){
        return apiUrl + titulo.replace(" ", "+") + apiKey;
    }

    private TituloRecord consultaApi(String titulo) {
        try {
            String url = montarUrl(titulo);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(body, TituloRecord.class);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Cacheable(value = "titulos", key = "#titulo")
    public Titulo buscarTitulo(String titulo){

        Optional<Titulo> tituloExistente = tituloRepository
                .findByTituloContainingIgnoreCase(titulo)
                .stream()
                .findFirst();

        if(tituloExistente.isPresent()){
            System.out.println("Retornando um título que ja existe no banco");
            return tituloExistente.get();
        }

        System.out.println("Buscando na API externa");
        TituloRecord tituloConsultado = this.consultaApi(titulo);

        Titulo tituloSerializado = new Titulo(tituloConsultado.titulo(),
                tituloConsultado.anoLancamento(),
                tituloConsultado.duracao(),
                tituloConsultado.genero(),
                tituloConsultado.sinopse(),
                tituloConsultado.poster());

        if(tituloConsultado.tipo().equals("movie")){
            tituloSerializado.setTipo(TipoTitulo.FILME);
        }

        if(tituloConsultado.tipo().equals("series")){
            tituloSerializado.setTipo(TipoTitulo.SERIE);
        }

        tituloRepository.save(tituloSerializado);

        return tituloSerializado;
    }


}
