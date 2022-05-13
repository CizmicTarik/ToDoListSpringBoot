package net.serapion.todolist.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import net.serapion.todolist.todo.entity.ToDoList;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "task")
@Getter
@Setter
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column
    String name;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    LocalDate endtime;

    @Column
    Boolean done;
//    Integer TDLID;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tdlid", referencedColumnName = "id")
    private ToDoList toDoList;

//    public ToDoList getToDoList() {
//        return toDoList;
//    }

    public Task() {
    }

    public Task(Integer id, String name, LocalDate endtime, Boolean done/*, Integer TDLID*/) {
        this.id = id;
        this.name = name;
        this.endtime = endtime;
        this.done = done;
//        this.TDLID = TDLID;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", endtime=" + endtime +
                ", done='" + done + '\'' +
//                ", TDLID=" + TDLID +
                '}';
    }
}
