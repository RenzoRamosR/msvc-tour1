package microservicio.Tour.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreatedTourEvent extends ApplicationEvent {
    private final Long tourId;

    public CreatedTourEvent(Object source, Long tourId) {
        super(source);
        this.tourId = tourId;
    }
}
