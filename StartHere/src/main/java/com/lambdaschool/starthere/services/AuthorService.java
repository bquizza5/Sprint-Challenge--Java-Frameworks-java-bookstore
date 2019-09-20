package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface AuthorService {

    List<Author> findAll(Pageable pageable);

    Author update(Author author, long authorid);

    void assign(long bookid, long authorid);

    void delete(long authorid);

    void save(Author author);




}
