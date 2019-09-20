package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Author;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.ArrayList;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    Author findAuthorByAuthorid(long authorid);

}
