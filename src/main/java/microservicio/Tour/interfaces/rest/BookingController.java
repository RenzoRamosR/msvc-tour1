package microservicio.Tour.interfaces.rest;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import microservicio.Tour.domain.model.commands.AddBookingDetailsCommand;
import microservicio.Tour.domain.services.ReservaCommandService;
import microservicio.Tour.interfaces.rest.resources.ReservaResource;
import microservicio.Tour.interfaces.rest.transform.ReservaResourceFromEntityAssembler;

@RestController
@RequestMapping(value="/Api/v1/FlavourTrip/booking", produces = MediaType.APPLICATION_JSON_VALUE)

public class BookingController {

    private final ReservaCommandService reservaCommandService;

    public BookingController(ReservaCommandService reservaCommandService) {
        this.reservaCommandService = reservaCommandService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResource> getReservaDetails(@PathVariable Long id) {
        var AddBookingDetailsCommand = new AddBookingDetailsCommand(id);
        var reserva = reservaCommandService.handle(AddBookingDetailsCommand);

        var reservaResource = ReservaResourceFromEntityAssembler.toResourceFromEntity(reserva);
        return ResponseEntity.ok(reservaResource);
    }

}
