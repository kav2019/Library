package library_project.controllers;


import library_project.dao.BookDAO;
import library_project.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String allBook(Model model){ //показать всех пользователей
        model.addAttribute("book", bookDAO.returnAllBook());
        return "book/all_books";
    }

    @GetMapping("/{id}") //показать одного пользователя
    public String getOneBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.returnOneBook(id));
        return "book/one_book";

    }

    @GetMapping("/{id}/edit") //показать страницу с полями для изменения юзера
    public String editBook(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.returnOneBook(id));
        return "book/edit_book";
    }

    @PatchMapping("/{id}") //сохранение измененного пользователя с редиректом
    public String saveEdit(@ModelAttribute("book")Book book, @PathVariable("id") int id){
        bookDAO.saveChangeBook(book, id);
        return "redirect:/book";
    }

    @GetMapping("/new_book") //страницу создания пользователя
    public String addBook(@ModelAttribute("book") Book book){
        return "book/add_book";
    }

    @PatchMapping("/new_book") //сохраняем новую книгу и редирект на главную страницу
    public String saveAddBook(@ModelAttribute("book") Book book){
        bookDAO.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/del")
    public String delBook(@PathVariable("id") int id){
        bookDAO.delBook(id);
        return "redirect:/book";
    }


}
