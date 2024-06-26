Feature: Task Service

  Scenario: Create a new task
    Given a task description "New Task"
    When I create the task
    Then the task should be created successfully

  Scenario: Remove an existing task
    Given an existing task with id 1
    When I remove the task
    Then the task should be removed successfully

  Scenario: Mark a task as done
    Given an existing task with id 1
    When I mark the task as done
    Then the task status should be "DONE"

  Scenario: Retrieve all tasks
    Given there are existing tasks
    When I retrieve all tasks
    Then I should get a list of all tasks