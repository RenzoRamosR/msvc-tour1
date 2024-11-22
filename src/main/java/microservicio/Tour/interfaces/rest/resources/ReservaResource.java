package microservicio.Tour.interfaces.rest.resources;

import microservicio.Tour.domain.model.valueobjects.ImageTour;

public record ReservaResource (String titleTour, ImageTour imageTour, Long duration, Long minPrice) {
}
