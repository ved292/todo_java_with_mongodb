package com.example.todowithmongo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.todowithmongo.Todos;
import com.example.todowithmongo.repository.TodoRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private TodoRepository todoRepo;   

    
    @GetMapping("/")
    public ResponseEntity<List<Todos>> get() {
        return ResponseEntity.ok(todoRepo.findByIsDeletedFalse());
    }   


    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody Todos entity) {
        entity.isDeleted=false;
        todoRepo.save(entity); 
        return ResponseEntity.status(HttpStatus.CREATED).body("Data Inserted");
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        return todoRepo.findById(id).map(existing->{
            existing.isDeleted = true;
            todoRepo.save(existing);
            return ResponseEntity.ok("Data deleted");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found"));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @RequestBody Todos entity) {
        return todoRepo.findById(id).map(existing -> {
            if (entity.title != null) {
            existing.title = entity.title;
        }
        if (entity.description != null) {
            existing.description = entity.description;
        }
        if (entity.isDeleted != null) {
            existing.isDeleted = entity.isDeleted;
        }
            todoRepo.save(existing);
            return ResponseEntity.ok("Entry Updated");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entry not found"));
    }
}