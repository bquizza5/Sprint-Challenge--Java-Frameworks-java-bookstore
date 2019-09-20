package com.lambdaschool.starthere.controllers;

        import com.lambdaschool.starthere.models.Book;
        import com.lambdaschool.starthere.services.BookService;
        import io.swagger.annotations.ApiImplicitParam;
        import io.swagger.annotations.ApiImplicitParams;
        import io.swagger.annotations.ApiOperation;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Pageable;
        import org.springframework.data.web.PageableDefault;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.servlet.http.HttpServletRequest;
        import java.util.List;


@RestController
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    BookService bookService;


    @ApiOperation(value = "returns all Books", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/books",
            produces = {"application/json"})
    public ResponseEntity<?> listBooks(HttpServletRequest request, @PageableDefault(page = 0,
            size = 5)
            Pageable pageable)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Book> allBooks = bookService.findAll(pageable);
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PutMapping(value = "data/books/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                Book updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("data/books/{id}")
    public ResponseEntity<?> deleteBookById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");

        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/data/books/{bookid}/authors/{authorid}")
    public ResponseEntity<?> assignAuthorToBook(HttpServletRequest request,
                                                @PathVariable
                                                        long bookid, @PathVariable long authorid)
    {
        logger.trace(request.getMethod()
                .toUpperCase() + " " + request.getRequestURI() + " accessed");



        bookService.assignAuthorToBook(authorid, bookid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
