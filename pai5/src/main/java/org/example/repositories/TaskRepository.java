package org.example.repositories;

import org.example.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByOrderById();

    List<Task> findByIsDoneOrderById(boolean isDone);

    List<Task> findByCostLessThanOrderById(double cost);

    List<Task> findByCostBetweenOrderById(double minCost, double maxCost);
}
