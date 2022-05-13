package net.serapion.todolist.todo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import net.serapion.todolist.task.entity.Task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "to_do_list")
@Getter
@Setter
public class ToDoList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public ToDoList() {
    }

    public ToDoList(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
//    @Bean(name="entityManagerFactory")
//    public LocalSessionFactoryBean sessionFactory() {
//        return new LocalSessionFactoryBean();
//    }
}
