package task.springboottesttask.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "departments_lectors",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "lector_id")
    )
    private Set<Lector> lectors = new HashSet<>();

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "departments_heads",
            joinColumns =
                    { @JoinColumn(name = "department_id", referencedColumnName = "id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "headOfDepartment_id", referencedColumnName = "id") })
    private Lector headOfDepartment;

    public Department(String name, Set<Lector> lectors, Lector headOfDepartment) {
        this.name = name;
        this.lectors = lectors;
        this.headOfDepartment = headOfDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return getId().equals(that.getId()) && getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lectors=" + lectors +
                ", headOfDepartment=" + headOfDepartment +
                '}';
    }
}
