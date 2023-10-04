package com.example.BooksApi.models;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookIsbn;
    private String bookName;
    private String publisher;

    //An author can have many books. where as a book is specific for an author
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "author", referencedColumnName = "authorId")
    private Author author;

}
