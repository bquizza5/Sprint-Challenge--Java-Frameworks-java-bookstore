package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Book getBookByBookid(long bookid);


    @Query(value = "UPDATE books SET authorid = :authorid WHERE bookid = :bookid", nativeQuery = true)
    void assignAuthorToBook(long authorid, long bookid);
}
