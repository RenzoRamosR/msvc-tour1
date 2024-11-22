package microservicio.Tour.interfaces.rest.transform;

import microservicio.Tour.domain.model.aggregates.Reserva;
import microservicio.Tour.interfaces.rest.resources.ReservaResource;

public class ReservaResourceFromEntityAssembler {
    public static  ReservaResource toResourceFromEntity(Reserva entity){
        return new ReservaResource(entity.getTitleTour(), entity.getImageTour(),entity.getDuration(),entity.getMinPrice());
    }

}
