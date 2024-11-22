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
    ShowTime findByMovieId(int id);
    @Query("SELECT s.room FROM ShowTime s WHERE s.movie.id = :movieId AND :startTime IN (s.startTime)")
    Room findRoomsByMovieAndStartTime(@Param("movieId") int movieId, @Param("startTime") LocalTime startTime);
}

