package com.acko.demo.controller;

import com.acko.demo.entity.Task;
import com.acko.demo.exception.ResourceNotFoundException;
import com.acko.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/list-tasks")
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @PostMapping("/add-task")
    public Task addNewTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping("/update-task/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Optional<Task> task1 = taskRepository.findById(id);

        if(task1.isEmpty()) {
            throw new ResourceNotFoundException();
        }else {
            Task temp = task1.get();
            temp.setDescription(task.getDescription());
            temp.setName(task.getName());
            temp.setStatus(task.getStatus());
            temp.setUser_id(task.getUser_id());
            return taskRepository.save(temp);
        }
    }

    @DeleteMapping("/deleter-task/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
