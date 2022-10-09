package library_project.controllers;


import library_project.models.Book;
import library_project.models.People;
import library_project.service.BookService;
import library_project.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
//    private final BookDAO bookDAO;
//    private final PeopleDAO peopleDAO;

//    @Autowired
//    public BookController(BookDAO bookDAO, PeopleDAO peopleDAO) {
//        this.bookDAO = bookDAO;
//        this.peopleDAO = peopleDAO;
//    }

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String allBook(Model model){ //показать всех пользователей
//        model.addAttribute("book", bookDAO.returnAllBook());
        model.addAttribute("book", bookService.returnAllBook());
        return "book/all_books";
    }

    @GetMapping("/{id}") //показать одн книгу со списком читателей и назначением новх
    public String getOneBook(Model model, @PathVariable("id") int id, @ModelAttribute("person") People people){
//        model.addAttribute("book", bookDAO.returnOneBook(id));
//        List<People> reader = bookDAO.getReadBook(id);//все читающие у данной книги
//        List<People> peopleForSetRead = peopleDAO.returnAllPeople(); //все люди

        model.addAttribute("book", bookService.returnOneBook(id));
        List<People> reader = bookService.getReadBook(id);//все читающие у данной книги
        List<People> peopleForSetRead = peopleService.returnAllPeople(); //все люди

        peopleForSetRead.removeAll(reader); //все кто еще не читает эту книгу

        model.addAttribute("people", peopleForSetRead); //люди в выпадающем списке которых можно назначить читающим
        model.addAttribute("reader", reader); //список людей которые читают данную книгу
        return "book/one_book";

    }

    @GetMapping("/{id}/edit") //показать страницу с полями для изменения юзера
    public String editBook(Model model, @PathVariable("id") int id){
//        model.addAttribute("book", bookDAO.returnOneBook(id));
        model.addAttribute("book", bookService.returnOneBook(id));
        return "book/edit_book";
    }

    @PatchMapping("/{id}") //сохранение измененного пользователя с редиректом
    public String saveEdit(@ModelAttribute("book")Book book, @PathVariable("id") int id){
//        bookDAO.saveChangeBook(book, id);
        bookService.saveChangeBook(book, id);
        return "redirect:/book";
    }

    @GetMapping("/new_book") //страницу создания пользователя
    public String addBook(@ModelAttribute("book") Book book){
        return "book/add_book";
    }

    @PatchMapping("/new_book") //сохраняем новую книгу и редирект на главную страницу
    public String saveAddBook(@ModelAttribute("book") Book book){
//        bookDAO.saveBook(book);

        bookService.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/del") //удаляет пользователя
    public String delBook(@PathVariable("id") int id){
//        bookDAO.delBook(id);

        bookService.delBook(id);
        return "redirect:/book";
    }

    @PostMapping("/{id}/set_reader") //добаляет к книге читающего
    public String setReader(@PathVariable("id") int idBook, @ModelAttribute("people")People people){
//        bookDAO.setReadPeople(people.getId(), idBook);

        bookService.setReadPeople(people.getId(), idBook);
        String html = "redirect:/book/" + idBook;
        return html;
    }




}
