package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.services.AuthorService;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.RoleService;
import com.lambdaschool.starthere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;


    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getUseremails()
          .add(new Useremail(u1, "admin@email.local"));
        u1.getUseremails()
          .add(new Useremail(u1, "admin@mymail.local"));

        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        u2.getUseremails()
          .add(new Useremail(u2, "cinnamon@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "hops@mymail.local"));
        u2.getUseremails()
          .add(new Useremail(u2, "bunny@email.local"));
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getUseremails()
          .add(new Useremail(u3, "barnbarn@email.local"));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);

        ArrayList<Author> authors = new ArrayList<>();
        Author a1 = (new Author("John", "Mitchell"));
        Author a2 = (new Author("Dan", "Brown"));
        Author a3 = (new Author("Jerry", "Poe"));
        Author a4 = (new Author("Wells", "Teague"));
        Author a5 = (new Author("George", "Gallinger"));
        Author a6 = (new Author("Ian", "Stewart"));
        authorService.save(a1);
        authorService.save(a2);
        authorService.save(a3);
        authorService.save(a4);
        authorService.save(a5);
        authorService.save(a6);



        ArrayList<Book> books = new ArrayList<>();
        Book b1 = (new Book("Flatterland", "9780738206752", 2001));
        Book b2 = (new Book("Digital Fortess", "9788489367012", 2007));
        Book b3 = (new Book("The Da Vinci Code", "9780307474278", 2009));
        Book b4 = (new Book("Essentials of Finance", "1314241651234", 2010));
        Book b5 = (new Book("Calling Texas Home", "1885171382134", 2000));

        bookService.save(b1);
        bookService.save(b2);
        bookService.save(b3);
        bookService.save(b4);
        bookService.save(b5);



    }

}