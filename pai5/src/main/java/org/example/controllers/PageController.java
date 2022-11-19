package org.example.controllers;

import org.example.entities.Task;
import org.example.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired
    public TaskRepository taskRepository;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping(value = {"/taskList", "/taskList/{lessThan}", "/taskList/{minCost}/{maxCost}"})
    @ResponseBody
    public String taskList(@PathVariable(value = "lessThan", required = false) Double lessThan, @PathVariable(value = "minCost", required = false) Double minCost, @PathVariable(value = "maxCost", required = false) Double maxCost) {
        if (lessThan != null) {
            return getFormattedTaskList(taskRepository.findByCostLessThan(lessThan));
        }
        if (minCost != null && maxCost != null) {
            return getFormattedTaskList(taskRepository.findByCostBetween(minCost, maxCost));
        }

        return getFormattedTaskList(taskRepository.findByOrderByIdAsc());
    }

    @RequestMapping("/taskListDone/{isDone}")
    @ResponseBody
    public String taskListByIsDone(@PathVariable(value = "isDone") Boolean isDone) {
        return getFormattedTaskList(taskRepository.findByIsDone(isDone));
    }


    @RequestMapping("/delete/{id:long}")
    public String deleteTask(@PathVariable("id") long id) {
        taskRepository.deleteById(id);
        return "redirect:/taskList";
    }

    @RequestMapping("/generate")
    @ResponseBody
    public String generateTestData() {
        ArrayList<Task> tasks = new ArrayList<>();
        double cost = 1000;
        boolean done = false;

        for (int i = 1; i < 11; i++) {
            Task task = new Task();
            task.setName("Zadanie " + i);
            task.setDescription("Opis czynnosci do wykonania w zadaniu " + i);
            task.setCost(cost);
            task.setDone(done);
            done = !done;
            cost += 200.5;
            tasks.add(task);
        }
        taskRepository.saveAll(tasks);
        return "Generated";
    }

    private String getFormattedTaskList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task i : tasks) {
            sb.append(i).append("<br>");
        }
        return sb.toString();
    }
}