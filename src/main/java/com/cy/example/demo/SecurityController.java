package com.cy.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/navpage")
    public String navpage(Model model) {
        return "navpage";
    }

    /*public void createUsers() {

        User user = new User();
        user.setUsername("applicant");
        user.setPassword("password");
        user.setId(1);

        Role role = new Role();
        role.setRole("APPLICANT");

        Collection<Role>  list = Arrays.asList(role);
        user.setRoles(list);


        User user2 = new User();
        user2.setUsername("employer");
        user2.setPassword("password");
        user.setId(2);

        Role role2 = new Role();
        role2.setRole("EMPLOYER");

        Collection<Role>  list2 = Arrays.asList(role2);
        user2.setRoles(list2);

        userRepository.save(user);
        userRepository.save(user2);

        return;
    }*/



    @RequestMapping("/")
    public String mainpage(Model model) {
        return "mainpage";
    }

    @RequestMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("user", userRepository.findAll());
        return "homepage";
    }

    @RequestMapping("/registerappl")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registerappl")
    public String processUser(@Valid User user, BindingResult result)
    {
        if (result.hasErrors()){
            return "registrationappl";
        }
        userService.saveApplicant(user);
        return "redirect:/";

    }

    @RequestMapping("/registeremp")
    public String registerEmp(Model model) {
        model.addAttribute("user", new User());
        return "registrationemp";
    }

    @PostMapping("/registeremp")
    public String processEmp(@Valid User user, BindingResult result)
    {
        if (result.hasErrors()){
            return "registrationemp";
        }
        userService.saveEmployer(user);
        return "redirect:/";

    }



    @RequestMapping("/roles")
    public @ResponseBody String showRoles(User user)
    {
        return "Roles available:"+roleRepository.findAll().toString();
    }


    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String secure(){
        return "homepage";
    }

    @RequestMapping("/ApplicantMenu")
    public String showapplmenu(Model model) {
        return "applmenupage";
    }


    @RequestMapping("/EmployerMenu")
    public String showempmenu(Model model) {
        return "empmenupage";
    }


}
