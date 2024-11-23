package cinema.repository;

import cinema.modal.entity.Room;
import cinema.modal.entity.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {
    @Query(value = """
    SELECT start_time
    FROM list_start_time r
    JOIN showtime s ON s.id = r.show_time_id
    WHERE s.id = :id
      AND list.start_time.show_time_id = Time
""", nativeQuery = true)
    ShowTime findByMovieId(int id);
    @Query(value = """
    SELECT r.* 
    FROM showtime s
    JOIN room r ON s.room_id = r.id
    JOIN list_start_time lst ON lst.showtime_id = s.id
    WHERE s.movie_id = :movieId
      AND lst.start_time = :startTime
""", nativeQuery = true)
    Room findRoomsByMovieAndStartTime(@Param("movieId") int movieId, @Param("startTime") LocalTime startTime);

}

