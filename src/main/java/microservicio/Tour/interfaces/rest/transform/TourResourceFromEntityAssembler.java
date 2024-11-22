package microservicio.Tour.interfaces.rest.transform;

import org.apache.commons.text.StringEscapeUtils;
import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.interfaces.rest.resources.TourResource;

public class TourResourceFromEntityAssembler {

    public static TourResource toResourceFromEntity(Tour entity) {
        Long restaurantId = entity.getRestaurantId();
        String imageUrl = entity.getImageTour().imageUrl();
        String sanitizedImageUrl = StringEscapeUtils.escapeHtml4(imageUrl);
        return new TourResource(entity.getId(),
                entity.getTitleTour(),
                sanitizedImageUrl,
                entity.getInstructor(),
                entity.getRating(),
                entity.getNRatings(),
                entity.getMinPrice(),
                entity.getCurrentPeople(),
                entity.getMaxPeople(),
                entity.getLanguage(),
                entity.getDuration(),
                entity.getItemsIncluded(),
                entity.getDate(),
                entity.getDescription(),
                entity.getTimes(),
                entity.getHours(),
                restaurantId);
    }
}
