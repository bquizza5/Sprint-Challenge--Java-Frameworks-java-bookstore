package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findAll(Pageable pageable);

    Book update(Book book, long bookid);

    void delete(long bookid);

    void save(Book book);

    void assignAuthorToBook(long authorid, long bookid);

}
