import domain.functional.enums.Status;
import domain.functional.model.Task;
import domain.functional.model.TaskList;
import domain.functional.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user = new User("John Doe", "johndoe@gmail.com");
        Task task = new Task("Description", "Title", "Task List",
                Status.TODO);
        TaskList taskList = new TaskList("Task List", user.getUserId(),
                List.of(task));

        taskList.printTaskList();
    }
}