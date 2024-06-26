package fr.esgi.controller.resource;
import fr.esgi.controller.utils.OptionCreator;
import fr.esgi.domain.functional.model.Task;
import fr.esgi.domain.ports.client.TaskApi;
import org.apache.commons.cli.*;

import java.util.List;
import java.util.Optional;

import static fr.esgi.controller.utils.ArgumentChecker.*;
import static fr.esgi.controller.utils.Displayer.displayTaskList;
import static fr.esgi.controller.utils.OptionChecker.isInvalidNumberOfOptions;

public class TaskResource {
    private final TaskApi taskApi;

    public TaskResource(TaskApi taskApi) {
        this.taskApi = taskApi;
    }

    public void dispatch(String[] args) {
        var loadOptions = OptionCreator.createOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(loadOptions, args);
            var options = cmd.getOptions();
            if (isInvalidNumberOfArguments(cmd.getArgs())) {
                System.err.println("No arguments or too many arguments");
                System.exit(1);
            }
            if (isInvalidNumberOfOptions(options)) {
                System.err.println("Only one option is allowed.");
                System.exit(1);
            }
            if (cmd.hasOption("a")) {
                create(cmd);
            }
            if (cmd.hasOption("d")) {
                remove(cmd);
            }
            if (cmd.hasOption("m")) {
                markAsDone(cmd);
            }
            if (cmd.hasOption("l")) {
                retrieveAllTasks(cmd);
            }
        } catch (ParseException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    public void create(CommandLine cmd) {
        if (isGotOneValue(cmd.getArgs())) {
            System.err.println("Invalid number of arguments for add command.");
            System.exit(1);
        }
        try {
            boolean created = taskApi.createTask(cmd.getOptionValue("a"));
            if (created) {
                System.out.println("Task created successfully.");
            } else {
                System.err.println("Task not created.");
            }
        } catch (Exception e) {
            System.err.println("Task not created, invalid description.");
            System.exit(1);
        }
    }

    public void remove(CommandLine cmd) {
        if (isGotOneValue(cmd.getArgs())) {
            System.err.println("Invalid number of arguments for delete command.");
            System.exit(1);
        }
        try {
            boolean delete = taskApi.removeTask(Integer.parseInt(cmd.getOptionValue("d")));
            if (delete) {
                System.out.println("Task deleted successfully.");
            } else {
                System.err.println("Task not deleted.");
            }
        } catch (Exception e) {
            System.err.println("Task not found.");
            System.exit(1);
        }
    }

    public void markAsDone(CommandLine cmd) {
        if (isGotOneValue(cmd.getArgs())) {
            System.err.println("Invalid number of arguments for done command.");
            System.exit(1);
        }
        try {
            boolean doneTask = taskApi.markAsDone(Integer.parseInt(cmd.getOptionValue("m")));
            if (doneTask) {
                System.out.println("Task marked as done.");
            } else {
                System.err.println("Task not marked as done.");
            }
        } catch (Exception e) {
            System.err.println("Task not found.");
            System.exit(1);
        }
    }

    public void retrieveAllTasks(CommandLine cmd) {
        try {
            Optional<List<Task>> listTask = taskApi.retrieveAllTasks();
            boolean isPresent = listTask.isPresent();
            if (isPresent) {
                displayTaskList(listTask.get());
            }
            else {
                System.err.println("No tasks found.");
                System.exit(1);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error with database.");
            System.exit(1);
        }
    }
}
