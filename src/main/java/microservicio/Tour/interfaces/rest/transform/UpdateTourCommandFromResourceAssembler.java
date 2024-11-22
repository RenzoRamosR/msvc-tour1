package microservicio.Tour.interfaces.rest.transform;

import microservicio.Tour.domain.model.commands.UpdateTourCommand;
import microservicio.Tour.domain.model.valueobjects.ImageTour;
import microservicio.Tour.interfaces.rest.resources.UpdateTourResource;

public class UpdateTourCommandFromResourceAssembler {
    public static UpdateTourCommand toCommandFromResource(Long id, UpdateTourResource resource){
        ImageTour image = new ImageTour(resource.imageTour());
        return new UpdateTourCommand(id,
                resource.titleTour(),
                image,
                resource.instructor(),
                resource.rating(),
                resource.nRatings(),
                resource.minPrice(),
                resource.currentPeople(),
                resource.maxPeople(),
                resource.language(),
                resource.duration(),
                resource.itemsIncluded(),
                resource.date(),
                resource.description(),
                resource.times(),
                resource.hours(),
                resource.restaurantId());
    }
}
