package net.serapion.todolist.task.control.repository;

import net.serapion.todolist.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("select task FROM Task task where task.endtime > :endTime")
    List<Task> findAllTasksBeforeTimestamp(@Param ("endTime") LocalDate endTime);
}

