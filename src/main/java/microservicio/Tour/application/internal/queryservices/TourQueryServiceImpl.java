package microservicio.Tour.application.internal.queryservices;

import org.springframework.stereotype.Service;
import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.domain.model.queries.GetAllToursQuery;
import microservicio.Tour.domain.model.queries.GetToursByIdQuery;
import microservicio.Tour.domain.services.TourQueryService;
import microservicio.Tour.insfractructure.persistence.jpa.repositories.TourRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TourQueryServiceImpl implements TourQueryService {
    private final TourRepository tourRepository;

    public TourQueryServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> handle(GetAllToursQuery query) {
        return tourRepository.findAll();
    }

    @Override
    public Optional<Tour> handle(GetToursByIdQuery query) {
        return tourRepository.findById(query.tourId());
    }
}
