package task.springboottesttask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import task.springboottesttask.models.Degree;
import task.springboottesttask.models.Department;
import task.springboottesttask.models.Lector;
import task.springboottesttask.repositories.DepartmentRepository;
import task.springboottesttask.repositories.LectorRepository;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class DataBaseRunner implements CommandLineRunner {

    private final LectorRepository lectorRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DataBaseRunner(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        this.lectorRepository = lectorRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void run(String... args) {
                Lector lector1 = new Lector("Oleksandr", "Zaichenko", Degree.ASSISTANT, BigDecimal.valueOf(1500));
        Lector lector2 = new Lector("Oleksandr", "Zavgorodniy", Degree.PROFESSOR, BigDecimal.valueOf(2500));
        Lector lector3 = new Lector("Volodymyr", "Khruschakov", Degree.ASSISTANT, BigDecimal.valueOf(1000));
        Lector lector4 = new Lector("Mariya", "Zhukova", Degree.ASSOCIATE_PROFESSOR, BigDecimal.valueOf(3000));
        lectorRepository.save(lector1);
        lectorRepository.save(lector2);
        lectorRepository.save(lector3);
        lectorRepository.save(lector4);
        System.out.println("Available lectors:");
        lectorRepository.findAll().forEach(l -> System.out.println("\t" + l.getName() + " " + l.getSurname()));

        departmentRepository.save(new Department("Computer science", Set.of(lector1, lector2), lector2));
        departmentRepository.save(new Department("Management", Set.of(lector1, lector3, lector4), lector4));
        System.out.println("Available departments:");
        departmentRepository.findAll().forEach(d -> System.out.println("\t" + d.getName()));
    }
}
