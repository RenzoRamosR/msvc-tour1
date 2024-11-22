package microservicio.Tour.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Price(Integer value) {
    public Price {
        if (value == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
