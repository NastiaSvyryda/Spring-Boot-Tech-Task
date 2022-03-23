package task.springboottesttask.utils;

import task.springboottesttask.models.Degree;
import task.springboottesttask.models.Department;
import task.springboottesttask.models.Lector;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public enum Command {
    HEAD("^\\s*Who\\s+is\\s+head\\s+of\\s+department\\s+([A-z0-9\\s]+)$") {
        @Override
        public String buildAnswer(Department department) {
            return "Head of " + department.getName() + " department is " +
                    department.getHeadOfDepartment().getName() + " " +
                    department.getHeadOfDepartment().getSurname();
        }
    },
    STATISTIC("^\\s*Show\\s+([A-z0-9\\s]+)\\s+statistics\\s*$") {
        @Override
        public String buildAnswer(Department department) {
            Set<Lector> lectorSet = department.getLectors();
            long asCount = lectorSet.stream().filter(l -> l.getDegree().equals(Degree.ASSISTANT)).count();
            long asPrCount = lectorSet.stream().filter(l -> l.getDegree().equals(Degree.ASSOCIATE_PROFESSOR)).count();
            long prCount = lectorSet.stream().filter(l -> l.getDegree().equals(Degree.PROFESSOR)).count();
            return "assistants - " + asCount + "\n" +
                    "associate professors - " + asPrCount + "\n" +
                    "professors - " + prCount;
        }
    },
    AVG_SALARY("^\\s*Show\\s+the\\s+average\\s+salary\\s+for\\s+the\\s+department\\s+([A-z0-9\\s]+)$") {
        @Override
        public String buildAnswer(Department department) {
            Set<Lector> lectorSet = department.getLectors();
            BigDecimal avgSalary = BigDecimal.valueOf(0);
            for (Lector lector : lectorSet) {
                avgSalary = avgSalary.add(lector.getSalary());
            }
            avgSalary = avgSalary.divide(BigDecimal.valueOf(lectorSet.size()), RoundingMode.UP);
            return "The average salary of " + department.getName() + " is " + avgSalary;
        }
    },
    EMP_COUNT("^\\s*Show\\s+count\\s+of\\s+employee\\s+for\\s+([A-z0-9\\s]+)$") {
        @Override
        public String buildAnswer(Department department) {
            return String.valueOf(department.getLectors().size());
        }
    },
    SEARCH("^\\s*Global\\s+search\\s+by\\s+([A-z0-9\\s]+)$") {
        @Override
        public String buildAnswer(Set<String> lectors) {
            return String.join(", ", lectors);
        }
    };

    private final String pattern;

    Command(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return this.pattern;
    }

    public String buildAnswer(Department department) {
        return null;
    }

    public String buildAnswer(Set<String> lectors) {
        return null;
    }
}
