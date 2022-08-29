package library_project.controllers;


import library_project.dao.PeopleDAO;
import library_project.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class PeopleControllers {
    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleControllers(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping() //показать всех пользователей
    public String allUsers(Model model){
        model.addAttribute("people", peopleDAO.returnAllPeople());
        return "people/all_users";
    }

    @GetMapping("/{id}") //показать одного пользователя
    public String getOnePeople(@PathVariable("id") int id, Model model){
        model.addAttribute("people", peopleDAO.returnOnePeople(id));
        model.addAttribute("books", peopleDAO.bookListUsing(id));
        return "people/one_user";
    }

    @GetMapping("/{id}/edit")  //показать страницу с полями для изменения юзера
    public String editPeople(Model model, @PathVariable("id") int id){
        model.addAttribute("people", peopleDAO.returnOnePeople(id));
        return "people/edit_users";
    }

    @PatchMapping("{id}") //сохранение измененного пользователя с редиректом
    public String saveEdit(@PathVariable("id") int id,  @ModelAttribute("people") People people){
        peopleDAO.saveChangePeople(people, id);
        return "redirect:/library";
    }

    @GetMapping("/new_people") //страницу создания пользователя
    public String addUsers(@ModelAttribute("people") People people){
        return "people/add_user";
    }

    @PatchMapping("/new_people")
    public String saveAddUser(@ModelAttribute("people") People people){ //сохраняем ового пользователя и редирект на главную страницу
        peopleDAO.savePeople(people);
        return "redirect:/library";
    }

    @GetMapping("/{id}/del") //даляем человека
    public String delUser(@PathVariable("id") int id){
        peopleDAO.delPeople(id);
        return "redirect:/library";
    }

    @GetMapping("/{idP}/del_book/{idB}")
    public String delBook(@PathVariable("idB") int idBook, @PathVariable("idP")int idPeople){ //удаляем книгу из списка
        peopleDAO.delBookOfList(idPeople, idBook);
        return "redirect:/library/"+idPeople;
    }
}
