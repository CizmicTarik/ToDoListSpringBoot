package net.serapion.todolist.task.boundary;

import net.serapion.todolist.task.control.repository.TaskRepository;
import net.serapion.todolist.task.control.service.TaskService;
import net.serapion.todolist.task.entity.Task;
import net.serapion.todolist.todo.control.repository.ToDoListRepository;
import net.serapion.todolist.todo.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/task_controller")
public class TaskController {
    private final TaskService taskService;
    private TaskRepository taskRepository;
    private final ToDoListRepository toDoListRepository;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository, ToDoListRepository toDoListRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.toDoListRepository = toDoListRepository;
    }

    @GetMapping
    public List<Task> getTask() {
        return taskService.getTask();
    }

    @GetMapping("/{timestamp}")
    public List<Task> checkTimestamp(@PathVariable("timestamp") LocalDate timeStamp) {
       return taskRepository.findAllTasksBeforeTimestamp(timeStamp);
    }

    @PostMapping("/addTask/{toDoListId}")
    public ResponseEntity<Object> addNewTask(@RequestBody Task task, @PathVariable("toDoListId") Integer toDoListId) {
//        taskRepository.save(task);
        Optional<ToDoList> optionalTask = toDoListRepository.findById(toDoListId);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        task.setToDoList(optionalTask.get());
        taskRepository.save(task);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{taskId}")
    public void deleteToDoList(@PathVariable("taskId") Integer taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping(path = "/{taskId}")
    public void updateTask(@PathVariable(value = "taskId") Integer taskId,
                           @RequestBody Task task) {
        taskService.updateTask(taskId, task);
    }
}
