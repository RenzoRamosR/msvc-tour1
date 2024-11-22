package microservicio.Tour.application.internal.commandservices;

import org.springframework.stereotype.Service;
import microservicio.Tour.application.internal.services.RestaurantService;
import microservicio.Tour.application.internal.services.resources.RestaurantResource;
import microservicio.Tour.domain.model.commands.AddRestaurantToTourCommand;
import microservicio.Tour.domain.model.commands.UpdateTourCommand;
import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.domain.model.commands.AddTourCommand;
import microservicio.Tour.domain.model.commands.DeleteTourCommand;
import microservicio.Tour.domain.services.TourCommandService;
import microservicio.Tour.insfractructure.persistence.jpa.repositories.TourRepository;

import java.util.Optional;

@Service
public class TourCommandServiceImpl implements TourCommandService {
    private final TourRepository tourRepository;
    private final RestaurantService restaurantService;  // se cambió el RestaurantRepository por RestaurantService ya que ahora vmaos a usar microservicios

    public TourCommandServiceImpl(TourRepository tourRepository, RestaurantService restaurantService) {
        this.tourRepository = tourRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public Long handle(AddTourCommand command) {
        // llamamos al microservicio de restaurant para obtener los detalles
        RestaurantResource restaurantDetails = restaurantService.getRestaurantData(command.restaurantId()); // ahora es un objeto RestaurantResource

        // Si necesitamos solo un dato del restaurante, por ejemplo el nombre
        String restaurantName = restaurantDetails.getName();

        // Creamos el tour con los datos del comando y almacenamos solo el restaurantId
        Tour tour = new Tour(
                command.titleTour(),
                command.imageTour(),
                command.instructor(),
                command.rating(),
                command.nRatings(),
                command.minPrice(),
                command.currentPeople(),
                command.maxPeople(),
                command.language(),
                command.duration(),
                command.itemsIncluded(),
                command.date(),
                command.description(),
                command.times(),
                command.hours(),
                command.restaurantId()
        );
        tourRepository.save(tour);
        return tour.getId();
    }

    @Override
    public Optional<Tour> handle(UpdateTourCommand command) {
        var result = tourRepository.findById(command.tourId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Tour does not exist");
        var tourToUpdate = result.get();

        // Llamada para el microservicio de restaurant
        RestaurantResource restaurantDetails = restaurantService.getRestaurantData(command.restaurantId());

        try {
            var updatedTour = tourRepository.save(tourToUpdate.updatedInformation(
                    command.titleTour(),
                    command.imageTour(),
                    command.instructor(),
                    command.rating(),
                    command.nRatings(),
                    command.minPrice(),
                    command.currentPeople(),
                    command.maxPeople(),
                    command.language(),
                    command.duration(),
                    command.itemsIncluded(),
                    command.date(),
                    command.description(),
                    command.times(),
                    command.hours(),
                    command.restaurantId()
            ));
            return Optional.of(updatedTour);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating tour: " + e.getMessage());
        }
    }

    @Override
    public Long handle(AddRestaurantToTourCommand command) {
        Tour tour = tourRepository.findById(command.tourId())
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        // Llamamos al servicio para obtener los detalles del restaurante
        RestaurantResource restaurantDetails = restaurantService.getRestaurantData(command.restaurantId().restaurantId()); // se accede al valor del long del restaurantid

        // se lamacena el restaurantId en el tour
        tour.setRestaurantId(command.restaurantId().restaurantId());  // aqui utilizamos el valor Long del RestaurantId
        tourRepository.save(tour);
        return tour.getId();
    }

    @Override
    public void handle(DeleteTourCommand command) {
        tourRepository.deleteById(command.tourId());
        System.out.println("Tour Delete");
    }
}
