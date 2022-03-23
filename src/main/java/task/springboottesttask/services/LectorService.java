package task.springboottesttask.services;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.springboottesttask.models.Lector;
import task.springboottesttask.repositories.LectorRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LectorService {
    private final LectorRepository lectorRepository;

    @Autowired
    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public Set<String> findByTemplateMatchedLectorsNames(String template) {
        List<Lector> lectors = lectorRepository.findAll();
        return lectors.stream().map(l -> l.getName() + " " + l.getSurname())
                .filter(lectorName -> lectorName.matches("(?i).*" + template + ".*")).collect(Collectors.toSet());
    }
}
