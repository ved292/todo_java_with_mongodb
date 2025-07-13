package com.example.todowithmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.todowithmongo.Todos;

public interface TodoRepository extends MongoRepository<Todos, String> {
    List<Todos> findByIsDeletedFalse();
}