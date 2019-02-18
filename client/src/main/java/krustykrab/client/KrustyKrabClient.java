package krustykrab.client;

import krustykrab.dto.IngredientesDto;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static java.util.Collections.singletonList;
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

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
