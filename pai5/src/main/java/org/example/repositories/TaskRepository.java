package org.example.repositories;

import org.example.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByOrderByIdAsc();

    List<Task> findByIsDone(boolean isDone);

    List<Task> findByCostLessThan(double cost);

    List<Task> findByCostBetween(double start, double end);
}
