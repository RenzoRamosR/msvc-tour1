package microservicio.Tour.interfaces.rest.transform;

import microservicio.Tour.domain.model.commands.AddTourCommand;
import microservicio.Tour.domain.model.valueobjects.ImageTour;
import microservicio.Tour.interfaces.rest.resources.CreateTourResource;

public class CreateTourCommandFromResourceAssembler {

    public static AddTourCommand toCommandFromResource(CreateTourResource resource){
        ImageTour image = new ImageTour(resource.image());
         return new AddTourCommand(resource.titleTour(),
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
