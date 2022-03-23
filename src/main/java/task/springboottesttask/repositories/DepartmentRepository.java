package task.springboottesttask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.springboottesttask.models.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName(String name);
}
