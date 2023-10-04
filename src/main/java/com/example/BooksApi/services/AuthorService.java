package com.example.BooksApi.services;

import com.example.BooksApi.Response.MessageResponse;
import com.example.BooksApi.models.Author;
import com.example.BooksApi.repositories.AuthorRepository;
import com.example.BooksApi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    //create a new author
    public ResponseEntity<?> createAuthor(Author newAuthor){
        if(authorRepository.existsByAuthorFirstName(newAuthor.getAuthorFirstName()) && authorRepository.existsByAuthorLastName(newAuthor.getAuthorLastName())){
            return ResponseEntity.badRequest().body(new MessageResponse("Author name already exsits!"));
        }
        else{
            Author author = new Author();
            author.setAuthorFirstName(newAuthor.getAuthorFirstName());
            author.setAuthorLastName(newAuthor.getAuthorLastName());
            authorRepository.save(author);
            return ResponseEntity.ok().body(new MessageResponse("Successfully saved"));
        }
    }
    //update an author by id
    public ResponseEntity<?> updateAuthorById(Integer id, Author updateAuthor){
        if(authorRepository.existsById(id)){
            return ResponseEntity.badRequest().body(new MessageResponse("Author is not found!!!"));
        }
        else{
            Author author = authorRepository.findById(id).get();
            author.setAuthorFirstName(updateAuthor.getAuthorFirstName());
            author.setAuthorLastName(updateAuthor.getAuthorLastName());
            authorRepository.save(author);
            return ResponseEntity.ok().body(new MessageResponse("Updated successfully"));
        }
    }
    //delete an author by id
    public void deleteAuthorById(Integer id){
        if(authorRepository.existsById(id)){
            bookRepository.deleteAllByAuthorAuthorId(id);
            authorRepository.deleteById(id);
        }
    }
    //get all authors
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    //get an author by the given id
    public ResponseEntity<?> getAuthorById(Integer id){
        if(authorRepository.existsById(id)){
            Author author = authorRepository.findById(id).get();
            return ResponseEntity.ok().body(author);
        }
        else{
            return ResponseEntity.ok().body(new MessageResponse("Author is not available!!!"));
        }
    }

}
