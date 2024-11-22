package microservicio.Tour.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import microservicio.Tour.domain.model.valueobjects.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tour")
public class Tour extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleTour;

    @Embedded
    private ImageTour imageTour;

    private String instructor;

    private Long rating;

    private Long nRatings;

    private Long minPrice;

    private Long currentPeople;

    private Long maxPeople;

    private String language;

    private Long duration;

    @ElementCollection
    private List<String> itemsIncluded;

    private String date;

    private String description;

    @ElementCollection
    private List<String> times;

    private String hours;

    // se quitó la relación con la entidad Restaurant
    private Long restaurantId;

    public Tour() {}

    public Tour updatedInformation(String titleTour, ImageTour imageTour, String instructor, Long rating, Long nRatings, Long minPrice, Long currentPeople, Long maxPeople, String language, Long duration, List<String> itemsIncluded, String date, String description, List<String> times, String hours, Long restaurantId) {
        this.titleTour = titleTour;
        this.imageTour = imageTour;
        this.instructor = instructor;
        this.rating = rating;
        this.nRatings = nRatings;
        this.minPrice = minPrice;
        this.currentPeople = currentPeople;
        this.maxPeople = maxPeople;
        this.language = language;
        this.duration = duration;
        this.itemsIncluded = itemsIncluded;
        this.date = date;
        this.description = description;
        this.times = times;
        this.hours = hours;
        this.restaurantId = restaurantId;  // ahora solo se almacena el ID del restaurante
        return this;
    }

    public Tour(String titleTour, ImageTour imageTour, String instructor, Long rating, Long nRatings, Long minPrice, Long currentPeople, Long maxPeople, String language, Long duration, List<String> itemsIncluded, String date, String description, List<String> times, String hours, Long restaurantId) {
        this.titleTour = titleTour;
        this.imageTour = imageTour;
        this.instructor = instructor;
        this.rating = rating;
        this.nRatings = nRatings;
        this.minPrice = minPrice;
        this.currentPeople = currentPeople;
        this.maxPeople = maxPeople;
        this.language = language;
        this.duration = duration;
        this.itemsIncluded = itemsIncluded;
        this.date = date;
        this.description = description;
        this.times = times;
        this.hours = hours;
        this.restaurantId = restaurantId;
    }
}
