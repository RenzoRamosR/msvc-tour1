package microservicio.Tour.domain.services;

import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.domain.model.queries.GetAllToursQuery;
import microservicio.Tour.domain.model.queries.GetToursByIdQuery;

import java.util.List;
import java.util.Optional;


public interface TourQueryService {
   List<Tour> handle(GetAllToursQuery query);
   Optional<Tour> handle(GetToursByIdQuery query);

}
