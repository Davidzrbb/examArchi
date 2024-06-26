package fr.esgi.controller.utils;

import fr.esgi.domain.functional.enums.Status;
import fr.esgi.domain.functional.model.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Displayer {
    public static void displayTask(Task task) {
        String taskString = String.format("[%d][%s] %s (%s)",
                task.getTaskId().intValue(),
                formatStatus(task.getStatus()),
                task.getDescription(),
                formatDate(task.getCreatedAt()));
        System.out.println(taskString);
    }

    public static void displayTaskList(List<Task> tasks) {
        for (Task task : tasks) {
            displayTask(task);
        }
    }

    public static String formatStatus(Status status) {
        return switch (status) {
            case TODO -> " ";
            case DONE -> "x";
        };
    }

    public static String formatDate(LocalDateTime creationDate) {
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(creationDate, now);

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (minutes < 60) {
            return minutes + " min";
        } else if (hours < 24) {
            return hours + " hour" + (hours > 1 ? "s" : "");
        } else {
            return days + " day" + (days > 1 ? "s" : "");
        }
    }
}
