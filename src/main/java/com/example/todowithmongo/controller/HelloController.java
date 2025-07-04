package com.example.todowithmongo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.todowithmongo.Todos;
import com.example.todowithmongo.repository.TodoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class HelloController {

    @Autowired
    private TodoRepository todoRepo;   

    
    @GetMapping("/")
    public List<Todos> find() {
        return todoRepo.findAll();
    }   


    @PostMapping("/post")
    public String insert(@RequestBody Todos entity) {
        todoRepo.save(entity);  
        return "Data Inserted";
    }
    

    @DeleteMapping("/delete/{id}")
    public String deleteTodoById(@PathVariable String id) {
        if (todoRepo.existsById(id)) {
            todoRepo.deleteById(id);
            return "Data Deleted";
        }
        return "Data Not Found";
    }


    @PutMapping("/put/{id}")
    public String updateTodo(@PathVariable String id, @RequestBody Todos entity) {
        return todoRepo.findById(id).map(existing -> {
            existing.title = entity.title;
            existing.description = entity.description;
            todoRepo.save(existing);
            return "Entry Updated";
        }).orElse("Entry not found");
    }
}