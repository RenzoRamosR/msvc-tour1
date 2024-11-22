package microservicio.Tour.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import microservicio.Tour.domain.model.entities.AuditableModel;
import microservicio.Tour.domain.model.entities.Tour;
import microservicio.Tour.domain.model.valueobjects.ImageTour;

//se eliminó la dependencia directa de la entidad user, se puede agregar opcionalmente cuando incluyamos el msvc-user

@Entity
@Getter
@Setter
@Table(name = "Reserva")
public class Reserva extends AuditableModel {  // se hereda la nueva clase AuditableModel
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    // Datos del tour copiados
    private String titleTour;

    @Embedded
    private ImageTour imageTour;

    private Long duration;

    private Long minPrice;

    public Reserva() {}

    public Reserva(Tour tour){
        this.tour = tour;
        if (tour != null) {
            this.titleTour = tour.getTitleTour();
            this.imageTour = tour.getImageTour();
            this.duration = tour.getDuration();
            this.minPrice = tour.getMinPrice();
        }
    }
}
