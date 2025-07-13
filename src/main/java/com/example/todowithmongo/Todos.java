package com.example.todowithmongo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// It is the schema of the todo
@Document(collection = "todos") 
public class Todos {
    @Id 
    public String number;

    public String title;
    public String description;
    public Boolean isDeleted;
    public Todos(String title,String description,Boolean isDeleted)
    {
        this.title = title;
        this.description = description;
        this.isDeleted = isDeleted;
    }
}