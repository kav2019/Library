package library_project.controllers;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibControllers {

    @GetMapping()
    public String allUsers(){
        System.out.println(1);
        return "people/all_users";
    }
}
