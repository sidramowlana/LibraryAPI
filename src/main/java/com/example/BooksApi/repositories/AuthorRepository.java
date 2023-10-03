package com.example.BooksApi.repositories;

import com.example.BooksApi.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByAuthorName(String authorName);
}
