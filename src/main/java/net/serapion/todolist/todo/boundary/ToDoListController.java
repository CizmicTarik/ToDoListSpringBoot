package net.serapion.todolist.todo.boundary;

import net.serapion.todolist.todo.control.repository.ToDoListRepository;
import net.serapion.todolist.todo.control.service.ToDoListService;
import net.serapion.todolist.todo.entity.ToDoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/todocontroller")
public class ToDoListController {
    private final ToDoListService toDoListService;
    //private final ToDoListRepository toDoListRepository;

    @Autowired
    public ToDoListController(ToDoListService toDoListService, ToDoListRepository toDoListRepository) {
        this.toDoListService = toDoListService;
        //    this.toDoListRepository = toDoListRepository;
    }

    @GetMapping
    public List<ToDoList> getToDoList() {
        return toDoListService.getToDoList();
    }

    @PostMapping("/addToDoList")
    public void addNewToDoList(@RequestBody ToDoList toDoList) {
        toDoListService.addNewToDoList(toDoList);
    }


    @DeleteMapping(path = "{todoId}")
    public void deleteToDoList(@PathVariable("todoId") Integer toDoListId) {
        toDoListService.deleteToDoList(toDoListId);
    }

    @PutMapping(path = "/{toDoId}")
    public void updateTask(@PathVariable(value = "toDoId") Integer toDoId,
                           @RequestBody ToDoList toDoList) {
        toDoListService.updateToDo(toDoId, toDoList);
    }
}