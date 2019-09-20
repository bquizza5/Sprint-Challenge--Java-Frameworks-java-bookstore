package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Transactional
    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Book update(Book book, long bookid) {


        Book currentBook = bookRepository.getBookByBookid(bookid);

        if (bookid == currentBook.getBookid())
        {
            if (book.getBooktitle() != null)
            {
                currentBook.setBooktitle(book.getBooktitle());
            }

            if (book.getIsbn() != null)
            {
                currentBook.setIsbn(book.getIsbn());
            }


            return bookRepository.save(currentBook);
        } else
        {
            throw new ResourceNotFoundException(bookid + " Not current book");
        }
    }

    @Transactional
    @Override
    public void delete(long bookid) {
        bookRepository.findById(bookid)
                .orElseThrow(() -> new ResourceNotFoundException("Author id " + bookid + " not found!"));
        bookRepository.deleteById(bookid);
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void assignAuthorToBook(long authorid, long bookid) {

        bookRepository.assignAuthorToBook(authorid, bookid);

    }
}
