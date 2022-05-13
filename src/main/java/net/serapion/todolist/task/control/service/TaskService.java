package net.serapion.todolist.task.control.service;


import net.serapion.todolist.task.control.repository.TaskRepository;
import net.serapion.todolist.task.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTask() {
        return taskRepository.findAll();
    }

    public void addNewTask(Task task) {
        taskRepository.save(task);
        System.out.println(task);

    }

    public void deleteTask(Integer taskId) {
        boolean exists = taskRepository.existsById(taskId);
        if (!exists) {
            throw new IllegalStateException(
                    "Task with ID " + taskId + " does not exist");
        }
        taskRepository.deleteById(taskId);
    }

    @Transactional
    public void updateTask(Integer taskId, Task task) {
        taskRepository.findById(taskId).orElseThrow(() ->
                new IllegalStateException("Task with ID " + taskId + " doesn't exist!"));
        taskRepository.save(task);
    }


}
