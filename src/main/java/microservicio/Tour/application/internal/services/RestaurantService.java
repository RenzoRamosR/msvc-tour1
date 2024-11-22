package microservicio.Tour.application.internal.services;

import microservicio.Tour.application.internal.services.resources.RestaurantResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class RestaurantService {

    private final RestTemplate restTemplate;

    public RestaurantService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestaurantResource getRestaurantData(Long restaurantId) {

        String url = "http://localhost:8081/restaurants/" + restaurantId;

        try {
            // para realizar la llamada al mcsv restaurant, esperando un RestaurantResource
            ResponseEntity<RestaurantResource> response = restTemplate.getForEntity(url, RestaurantResource.class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            // manejo básico de errores
            throw new RuntimeException("Error al obtener el restaurante con id " + restaurantId + ": " + e.getMessage(), e);
        }
    }
}
