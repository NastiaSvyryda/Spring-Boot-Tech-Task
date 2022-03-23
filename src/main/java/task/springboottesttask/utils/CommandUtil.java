package task.springboottesttask.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.springboottesttask.models.Department;
import task.springboottesttask.services.DepartmentService;
import task.springboottesttask.services.LectorService;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandUtil {

    private final LectorService lectorService;
    private final DepartmentService departmentService;

    @Autowired
    public CommandUtil(LectorService lectorService, DepartmentService departmentService) {
        this.lectorService = lectorService;
        this.departmentService = departmentService;
    }

    public String getAnswer(String inputCommand) {
        for (Command command : Command.values()) {
            Matcher matcher = Pattern.compile(command.getPattern()).matcher(inputCommand);
            if (matcher.find()) {
                String argument = matcher.group(1).trim();
                if (command.equals(Command.SEARCH)) {
                    Set<String> matchedLectorsNames = lectorService.findByTemplateMatchedLectorsNames(argument);
                    return matchedLectorsNames.size() > 0 ? command.buildAnswer(matchedLectorsNames) : "Matches not found!";
                }
                Department department = departmentService.findDepartmentByName(argument);
                return department != null ? command.buildAnswer(department) : "Department not found!";
            }
        }
        return "Command not found!";
    }
}
