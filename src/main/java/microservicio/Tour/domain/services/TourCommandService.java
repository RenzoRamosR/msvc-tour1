package microservicio.Tour.domain.services;

import microservicio.Tour.domain.model.commands.AddRestaurantToTourCommand;
import microservicio.Tour.domain.model.commands.AddTourCommand;
import microservicio.Tour.domain.model.commands.DeleteTourCommand;
import microservicio.Tour.domain.model.commands.UpdateTourCommand;
import microservicio.Tour.domain.model.entities.Tour;

import java.util.Optional;

public interface TourCommandService {
    Long handle(AddTourCommand command);
    Optional<Tour> handle (UpdateTourCommand command);

    Long handle(AddRestaurantToTourCommand command);
    void handle(DeleteTourCommand command);
}
