package fr.esgi.domain.functional.service.validation;

import fr.esgi.domain.functional.model.Task;

public class TaskValidation {
    public static boolean validateDescription(String description) {
        return description != null && !description.isEmpty();
    }

    public static boolean validateId(Number taskId) {
        return taskId != null && taskId.intValue() > 0;
    }

    public static boolean validateTask(Task task) {
        return task != null && validateDescription(task.getDescription()) && validateId(task.getTaskId());
    }
}
