package com.example.BooksApi.controllers;

import com.example.BooksApi.models.Author;
import com.example.BooksApi.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/author")
@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //CREATE --------------------------------
    @PostMapping(value = "/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author newAuthor) {
        return authorService.createAuthor(newAuthor);
    }

    //READ OR GET ---------------------------
    //get all authors
    @GetMapping(value = "/authorAll")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    //get an author by the respective isbn
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Integer id) {
        return authorService.getAuthorById(id);
    }

    //UPDATE --------------------------------
    @GetMapping(value = "/update/{id}")
    public ResponseEntity<?> updateAAuthorById(@PathVariable Integer id, @RequestBody Author updateAuthor) {
        return authorService.updateAuthorById(id, updateAuthor);
    }


    //DELETE --------------------------------
    @DeleteMapping(value = "/delete/{id}")
    public void deleteAuthorById(@PathVariable Integer id) {
        authorService.deleteAuthorById(id);
    }

}