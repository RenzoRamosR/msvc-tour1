package microservicio.Tour.domain.model.commands;

import microservicio.Tour.domain.model.valueobjects.RestaurantId;

public record AddRestaurantToTourCommand (Long tourId, RestaurantId restaurantId) {
}
