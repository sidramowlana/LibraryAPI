package com.example.BooksApi.controllers;

import com.example.BooksApi.models.Book;
import com.example.BooksApi.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/books")
@RestController
public class BookController {
    private BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    //CREATE --------------------------------
    @PostMapping(value="/create")
    public ResponseEntity<?> createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);
    }

    //READ OR GET ---------------------------
    //get all books
    @GetMapping(value = "/bookAll")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //get a book by the respective isbn
    @GetMapping(value="/{isbn}")
    public ResponseEntity<?> getABook(@PathVariable Integer isbn){
        return bookService.getABook(isbn);
    }

    //UPDATE --------------------------------
    @GetMapping(value="/update/{isbn}")
    public ResponseEntity<?> updateABookByIsbn(@PathVariable Integer isbn, @RequestBody Book updateBook){
        return bookService.updateABookByIsbn(isbn, updateBook);
    }


    //DELETE --------------------------------
    @DeleteMapping(value = "/delete/{isbn}")
    public ResponseEntity<?> deleteBookByIsbn(@PathVariable Integer isbn){
        return bookService.deleteBookByIsbn(isbn);
    }



}
