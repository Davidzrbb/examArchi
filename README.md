# ToDoList

Command line todo list application written in Java.

After clonning the repository, you can build the project using the following command:


  - [Testing](#testing)
  - [Commands](#commands)
    - [Add](#add)
    - [List](#list)
    - [Remove](#remove)
    - [Mark as done](#done)
___

## Testing

To launch the tests you need to add "Cucumber for Java" plugin to your IDE.

___
## Commands

```bash
java -jar ToDoList.jar -[command] [arguments]
```

### Add
```bash
java -jar ToDoList.jar -add [task description]
```

### List
```bash
java -jar ToDoList.jar -l
```

### Remove
```bash
java -jar ToDoList.jar -d [task number]
```

### Mark as done
```bash
java -jar ToDoList.jar -m [task number]
```