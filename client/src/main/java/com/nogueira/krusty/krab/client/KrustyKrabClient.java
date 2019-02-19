package com.nogueira.krusty.krab.client;

import com.nogueira.krusty.krab.client.dto.IngredientesDto;
import com.nogueira.krusty.krab.model.Lanche;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@NoArgsConstructor
@Builder
public class KrustyKrabClient {

    final String ROOT_URI = "http://localhost:8080/lanches";
    private RestTemplate client;

    public KrustyKrabClient(RestTemplate client) {
        this.client = client;
    }

    public ResponseEntity<Double> getPrice(IngredientesDto ingredientes) {
        HttpEntity<IngredientesDto> requestEntity = new HttpEntity<>(ingredientes, getHttpHeaders());

        return client.exchange(ROOT_URI + "/price", POST, requestEntity, Double.class);
    }

    public ResponseEntity<List<Lanche>> getLanchesInCardapio() {
        return client.exchange(ROOT_URI + "/cardapio",
                GET, null, new ParameterizedTypeReference<List<Lanche>>() {
                });
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
