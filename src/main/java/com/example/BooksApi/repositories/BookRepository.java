package com.example.BooksApi.repositories;

import com.example.BooksApi.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByBookIsbn(Integer isbn);
    boolean existsByBookIsbn(Integer isbn);
    List<Book> findByAuthorAuthorId(Integer authorId);
    void deleteByBookIsbn(Integer isbn);
    void deleteAllByAuthorAuthorId(Integer id);
}
