package microservicio.Tour.insfractructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservicio.Tour.domain.model.aggregates.Reserva;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
