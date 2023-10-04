package com.example.BooksApi.services;

import com.example.BooksApi.Response.MessageResponse;
import com.example.BooksApi.models.Book;
import com.example.BooksApi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //create a new book for a specific author
    public ResponseEntity<?> createBook(Book newBook){
        if(bookRepository.existsByBookIsbn(newBook.getBookIsbn())){
            return ResponseEntity.badRequest().body(new MessageResponse("Book already exsits!!!"));
        }
        else{
            Book book = new Book();
            book.setBookIsbn(newBook.getBookIsbn());
            book.setBookName(newBook.getBookName());
            book.setPublishDate(newBook.getPublishDate());
            book.setPublisher(newBook.getPublisher());
            bookRepository.save(book);
            return ResponseEntity.ok().body(new MessageResponse("Successfully saved"));
        }
    }
    //update a book by isbn
    public ResponseEntity<?> updateABookByIsbn(Integer isbn, Book updateBook){
        if(bookRepository.existsByBookIsbn(isbn)){
            return ResponseEntity.badRequest().body(new MessageResponse("Book is not found!!!"));
        }
        else{
            Book book = bookRepository.findByBookIsbn(isbn);
            book.setBookName(updateBook.getBookName());
            book.setPublishDate(updateBook.getPublishDate());
            book.setPublisher(updateBook.getPublisher());
            bookRepository.save(book);
            return ResponseEntity.ok().body(new MessageResponse("Updated successfully"));
        }
    }
    //delete a book by isbn
    public void deleteBookByIsbn(Integer isbn){
        if(bookRepository.existsByBookIsbn(isbn)){
            bookRepository.deleteByBookIsbn(isbn);
        }
    }
    //get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //get a book by the given isbn
    public ResponseEntity<?> getABook(Integer isbn){
        if(bookRepository.existsByBookIsbn(isbn)){
            Book book = bookRepository.findByBookIsbn(isbn);
            return ResponseEntity.ok().body(book);
        }
        else{
            return ResponseEntity.ok().body(new MessageResponse("Book is not available!!!"));
        }
    }
}
