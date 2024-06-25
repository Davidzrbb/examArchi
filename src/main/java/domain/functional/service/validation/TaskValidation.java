package domain.functional.service.validation;

public class TaskValidation {
    public static boolean validateTitle(String title) {
        return title != null && !title.isEmpty();
    }

    public static boolean validateId(Number id) {
        return id != null && id.intValue() > 0;
    }
}
