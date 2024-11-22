package microservicio.Tour.domain.model.valueobjects;

public record RestaurantId(Long restaurantId) {

    public RestaurantId {
        if (restaurantId == null) {
            throw new IllegalArgumentException("Restaurant ID cannot be null");
        }
        if (restaurantId < 0) {
            throw new IllegalArgumentException("Restaurant ID cannot be negative");
        }
    }

    /**public RestaurantId() {
        this(0L);  // para un constructor sin argumentos, es opcional
    } **/
}
