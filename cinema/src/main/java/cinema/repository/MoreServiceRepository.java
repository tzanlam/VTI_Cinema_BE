package cinema.repository;

import cinema.modal.entity.MoreService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoreServiceRepository extends JpaRepository<MoreService, Integer> {
}
