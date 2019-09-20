package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;


@ApiModel(value = "book", description = "the Book Entity")
@Entity
@Table(name = "books")
public class Book extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long bookid;

    String booktitle;
    String isbn;
    int copy;

    @ManyToOne
    @JoinColumn(name = "authorid",
            nullable = true)
    @JsonIgnoreProperties("books")
    private Author author;



    public Book() {
    }

    public Book(String booktitle, String isbn, int copy) {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copy = copy;
    }

    public long getBookid() {
        return bookid;
    }

    public void setBookid(long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopy() {
        return copy;
    }

    public void setCopy(int copy) {
        this.copy = copy;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
