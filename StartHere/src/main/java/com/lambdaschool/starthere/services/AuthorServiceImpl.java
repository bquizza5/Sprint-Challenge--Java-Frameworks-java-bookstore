package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Author;

import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Transactional
    @Override
    public List<Author> findAll(Pageable pageable) {
        List<Author> list = new ArrayList<>();
        authorRepository.findAll(pageable).iterator().forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Author update(Author author, long authorid) {


        Author currentAuthor = authorRepository.findAuthorByAuthorid(authorid);

        if (authorid == currentAuthor.getAuthorid())
        {
            if (author.getFirstname() != null)
            {
                currentAuthor.setFirstname(author.getFirstname());
            }

            if (author.getLastname() != null)
            {
                currentAuthor.setLastname(author.getLastname());
            }


            return authorRepository.save(currentAuthor);
        } else
        {
            throw new ResourceNotFoundException(authorid + " Not current author");
        }
    }

    @Transactional
    @Override
    public void assign(long bookid, long authorid) {

    }

    @Transactional
    @Override
    public void delete(long authorid) {
        authorRepository.findById(authorid)
                .orElseThrow(() -> new ResourceNotFoundException("Author id " + authorid + " not found!"));
        authorRepository.deleteById(authorid);
    }

    @Transactional
    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

}
