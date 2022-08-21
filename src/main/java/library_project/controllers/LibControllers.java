package library_project.controllers;


import library_project.dao.PeopleDAO;
import library_project.models.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/library")
public class LibControllers {
    private final PeopleDAO peopleDAO;

    @Autowired
    public LibControllers(PeopleDAO peopleDAO) {
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
        return "people/one_people";
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
}
