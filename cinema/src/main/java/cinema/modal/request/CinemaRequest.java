package cinema.modal.request;

import cinema.modal.entity.Cinema;
import cinema.modal.entity.constant.StatusCinema;
import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CinemaRequest {
    @NotNull
    private String name;
    private List<String> image;
    @NotNull
    private String location;

    public Cinema asCinema() {
        Cinema cinema = new Cinema();
        cinema.setName(name);
        cinema.setImage(image);
        cinema.setLocation(location);
        cinema.setStatus(StatusCinema.OPEN);
        return cinema;
    }

    public Cinema updateCinema(Cinema cinema) {
        cinema.setName(name);
        cinema.setImage(image);
        cinema.setLocation(location);
        return cinema;
    }
}
