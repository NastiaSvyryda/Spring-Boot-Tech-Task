package task.springboottesttask.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @SequenceGenerator(
            name = "lector_sequence",
            sequenceName = "lector_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lector_sequence"
    )
    private Long id;

    private String name;

    private String surname;

    private Degree degree;

    private BigDecimal salary;

    @ManyToMany()
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "lector_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    @OneToOne(mappedBy = "headOfDepartment")
    private Department headedDepartment;

    public Lector(String name, String surname, Degree degree, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.degree = degree;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lector lector = (Lector) o;
        return getId().equals(lector.getId()) && getName().equals(lector.getName()) && getSurname().equals(lector.getSurname()) && getDegree() == lector.getDegree() && getSalary().equals(lector.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getDegree(), getSalary());
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", degree=" + degree.getDegreeName() +
                ", salary=" + salary +
                '}';
    }
}
