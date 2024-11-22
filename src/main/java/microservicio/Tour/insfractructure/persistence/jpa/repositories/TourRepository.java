package microservicio.Tour.insfractructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservicio.Tour.domain.model.entities.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
      boolean existsById(Long id);
}
