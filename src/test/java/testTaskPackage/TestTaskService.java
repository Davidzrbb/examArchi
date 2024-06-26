package testTaskPackage;


import fr.esgi.domain.functional.enums.Status;
import fr.esgi.domain.functional.model.Task;
import fr.esgi.domain.functional.service.task_services.TaskService;
import fr.esgi.domain.ports.data.PersistencePort;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class TestTaskService {

    private TaskService taskService;
    private PersistencePort persistencePort;
    private boolean result;
    private Task task;
    private List<Task> tasks;

    @Given("a task description {string}")
    public void a_task_description(String description) {
        persistencePort = mock(PersistencePort.class);
        when(persistencePort.getLastTaskId()).thenReturn(Optional.of(0));
        taskService = new TaskService(persistencePort);
        task = new Task(1, description, Status.TODO);
    }

    @When("I create the task")
    public void i_create_the_task() {
        when(persistencePort.save(any(Task.class))).thenReturn(true);
        result = taskService.createTask(task.getDescription());
    }

    @Then("the task should be created successfully")
    public void the_task_should_be_created_successfully() {
        Assert.assertTrue(result);
    }

    @Given("an existing task with id {int}")
    public void an_existing_task_with_id(Integer id) {
        persistencePort = mock(PersistencePort.class);
        taskService = new TaskService(persistencePort);
        task = new Task(id, "Existing Task", Status.TODO);
        when(persistencePort.findTaskById(id)).thenReturn(Optional.of(task));
    }

    @When("I remove the task")
    public void i_remove_the_task() {
        when(persistencePort.remove(any(Task.class))).thenReturn(true);
        result = taskService.removeTask(task.getTaskId());
    }

    @Then("the task should be removed successfully")
    public void the_task_should_be_removed_successfully() {
        Assert.assertTrue(result);
    }

    @When("I mark the task as done")
    public void i_mark_the_task_as_done() {
        when(persistencePort.updateStatus(task.getTaskId(), Status.DONE)).thenReturn(true);
        result = taskService.markAsDone(task.getTaskId());
    }

    @Then("the task status should be {string}")
    public void the_task_status_should_be(String status) {
        Assert.assertTrue(result);
        Assert.assertEquals(Status.valueOf(status), task.getStatus());
    }

    @Given("there are existing tasks")
    public void there_are_existing_tasks() {
        persistencePort = mock(PersistencePort.class);
        taskService = new TaskService(persistencePort);
        tasks = new ArrayList<>();
        tasks.add(new Task(1, "Task 1", Status.TODO));
        tasks.add(new Task(2, "Task 2", Status.DONE));
        when(persistencePort.retrieveAllTasks()).thenReturn(Optional.of(tasks));
    }

    @When("I retrieve all tasks")
    public void i_retrieve_all_tasks() {
        Optional<List<Task>> retrievedTasks = taskService.retrieveAllTasks();
        tasks = retrievedTasks.orElse(new ArrayList<>());
    }

    @Then("I should get a list of all tasks")
    public void i_should_get_a_list_of_all_tasks() {
        Assert.assertFalse(tasks.isEmpty());
        Assert.assertEquals(2, tasks.size());
    }
}