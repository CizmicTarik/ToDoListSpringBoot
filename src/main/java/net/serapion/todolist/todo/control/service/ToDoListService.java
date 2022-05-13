package net.serapion.todolist.todo.control.service;

import net.serapion.todolist.todo.control.repository.ToDoListRepository;
import net.serapion.todolist.todo.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public List<ToDoList> getToDoList() {
        return toDoListRepository.findAll();
    }

    public void addNewToDoList(ToDoList toDoList) {
        getToDoList().add(toDoList);
        toDoListRepository.save(toDoList);
        System.out.println(toDoList);
    }

    public void deleteToDoList(Integer toDoListId) {
        boolean exists = toDoListRepository.existsById(toDoListId);
        if (!exists) {
            throw new IllegalStateException(
                    "To Do List with ID " + toDoListId + "does not exist");
        }
        toDoListRepository.deleteById(toDoListId);
    }

    public void updateToDo(Integer toDoId, ToDoList toDoList) {
        toDoListRepository.findById(toDoId).orElseThrow(() ->
                new IllegalStateException("ToDoList with ID " + toDoId + " doesn't exist!"));
        toDoListRepository.save(toDoList);
    }
}
