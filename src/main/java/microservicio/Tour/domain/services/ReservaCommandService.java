package microservicio.Tour.domain.services;

import microservicio.Tour.domain.model.aggregates.Reserva;
import microservicio.Tour.domain.model.commands.AddBookingDetailsCommand;

public interface ReservaCommandService {
    Reserva handle (AddBookingDetailsCommand command);
}
