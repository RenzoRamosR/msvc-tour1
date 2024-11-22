package microservicio.Tour.domain.model.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record ImageTour(String imageUrl) {

    public ImageTour {
        if (!imageUrl.startsWith("http://") && !imageUrl.startsWith("https://")) {
            throw new IllegalArgumentException("URL de imagen debe comenzar con http:// o https://");
        }
    }
}
