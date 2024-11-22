package microservicio.Tour.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import microservicio.Tour.domain.exceptions.TourNotFoundException;
import microservicio.Tour.domain.model.commands.AddRestaurantToTourCommand;
import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.domain.model.commands.DeleteTourCommand;
import microservicio.Tour.domain.model.queries.GetAllToursQuery;
import microservicio.Tour.domain.model.queries.GetToursByIdQuery;
import microservicio.Tour.domain.services.TourCommandService;
import microservicio.Tour.domain.services.TourQueryService;
import microservicio.Tour.interfaces.rest.resources.CreateTourResource;
import microservicio.Tour.interfaces.rest.resources.TourResource;
import microservicio.Tour.interfaces.rest.resources.UpdateTourResource;
import microservicio.Tour.interfaces.rest.transform.CreateTourCommandFromResourceAssembler;
import microservicio.Tour.interfaces.rest.transform.TourResourceFromEntityAssembler;
import microservicio.Tour.interfaces.rest.transform.UpdateTourCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/Api/v1/FlavourTrip/tour", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Tour", description = "Tour Managment Endpoints")
public class TourController {
    private final TourCommandService tourCommandService;
    private final TourQueryService tourQueryService;

    public TourController(TourCommandService tourCommandService, TourQueryService tourQueryService){
        this.tourCommandService = tourCommandService;
        this.tourQueryService = tourQueryService;
    }
    @Operation(summary = "Crear un Tour")
    @PostMapping("/create")
    public ResponseEntity<TourResource> createTour(@RequestBody CreateTourResource createTourResource){
        var createTourCommand = CreateTourCommandFromResourceAssembler.toCommandFromResource(createTourResource);
        var tourId = tourCommandService.handle(createTourCommand);

        if (tourId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getTourByIdQuery = new GetToursByIdQuery(tourId);
        var tour = tourQueryService.handle(getTourByIdQuery);

        if (tour.isEmpty())
            return ResponseEntity.badRequest().build();
        var tourResource = TourResourceFromEntityAssembler.toResourceFromEntity(tour.get());
        return new ResponseEntity<>(tourResource, HttpStatus.CREATED);
    }
    @Operation(summary = "Obtener lista de Tours disponibles")
    @GetMapping
    public ResponseEntity<List<TourResource>> getAllTours(){
        var getAllToursQuery = new GetAllToursQuery();
        var tours = tourQueryService.handle(getAllToursQuery);
        var tourResources= tours.stream().map(TourResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(tourResources);
    }
    @Operation(summary = "Obtener lista de Tours disponibles por Id")
    @GetMapping("/{id}")
    public ResponseEntity<TourResource> getTourById(@PathVariable Long id){
        var getTourByIdQuery = new GetToursByIdQuery(id);
        var tour = tourQueryService.handle(getTourByIdQuery);

        if (tour.isEmpty())
            return ResponseEntity.badRequest().build();
        var tourResource = TourResourceFromEntityAssembler.toResourceFromEntity(tour.get());
        return ResponseEntity.ok(tourResource);
    }
    @Operation(summary = "Modificar un Tour existente por Id")
    @PutMapping("/{id}")
    public ResponseEntity<TourResource> updateTour(@PathVariable Long id, @RequestBody UpdateTourResource updateTourResource){
        var updateTourCommand = UpdateTourCommandFromResourceAssembler.toCommandFromResource(id,updateTourResource);
        var updatedTour = tourCommandService.handle(updateTourCommand);

        if(updatedTour.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var tourResource = TourResourceFromEntityAssembler.toResourceFromEntity(updatedTour.get());
        return ResponseEntity.ok(tourResource);
    }
    @Operation(summary = "Eliminar un Tour por Id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTour(@PathVariable Long id){
        var deleteTourCommand = new DeleteTourCommand(id);
        tourCommandService.handle(deleteTourCommand);
        return ResponseEntity.ok("Tour with given id successfully deleted ");
    }

}
