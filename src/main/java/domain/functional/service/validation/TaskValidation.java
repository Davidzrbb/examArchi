package domain.functional.service.validation;

public class TaskValidation {
    public static boolean validateTitle(String title) {
        return title != null && !title.isEmpty();
    }

    public static boolean validateDescription(String description) {
        return description != null && !description.isEmpty();
    }
}
