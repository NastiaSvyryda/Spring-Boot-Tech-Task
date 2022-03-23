package task.springboottesttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.springboottesttask.models.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}
