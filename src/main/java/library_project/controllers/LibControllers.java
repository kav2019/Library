package library_project.controllers;


import library_project.dao.PeopleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibControllers {
    private final PeopleDAO peopleDAO;

    @Autowired
    public LibControllers(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping()
    public String allUsers(Model model){
        model.addAttribute("people", peopleDAO.returnAllPeople());
        return "people/all_users";
    }
}
