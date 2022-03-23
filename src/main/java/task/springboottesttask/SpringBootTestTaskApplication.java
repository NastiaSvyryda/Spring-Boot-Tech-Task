package task.springboottesttask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import task.springboottesttask.utils.CommandUtil;

import java.io.Console;

@SpringBootApplication
public class SpringBootTestTaskApplication{

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootTestTaskApplication.class);

    private static CommandUtil commandUtil;

    @Autowired
    public SpringBootTestTaskApplication(CommandUtil commandUtil) {
        SpringBootTestTaskApplication.commandUtil = commandUtil;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootTestTaskApplication.class, args);
        Console console = System.console();

        System.out.println("Available commands:\n" +
                "\tWho is head of department {department_name}\n" +
                "\tShow {department_name} statistics\n" +
                "\tShow the average salary for the department {department_name}\n" +
                "\tShow count of employee for {department_name}\n" +
                "\tGlobal search by {template}\n" +
                "\tExit\n");
        while (true) {
            String inputCommand = console.readLine("Enter command: ");
            if (inputCommand.equals("Exit"))
                break;
            System.out.println("Answer: " + commandUtil.getAnswer(inputCommand));
        }
        LOG.info("APPLICATION FINISHED");
    }
}
