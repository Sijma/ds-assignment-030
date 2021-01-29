package gr.hua.dit.ds.dsassignment030.controllers;

import gr.hua.dit.ds.dsassignment030.Entities.User;
import gr.hua.dit.ds.dsassignment030.Services.DataServices;
import gr.hua.dit.ds.dsassignment030.Users.Secretary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
public class MainPageController {
    @Autowired
    private DataServices service;

    @GetMapping("/")
    public String showIndex()
    {
        return "index";
    }

    @GetMapping("/logout")
    public void logout() { }

    @Secured({"ROLE_ADMIN", "ROLE_SECRETARY"})
    @GetMapping("/get-info")
    public String showGetPoints() { return "getDocInfo";}

   @Secured({"ROLE_ADMIN", "ROLE_SECRETARY"})
    @PostMapping("/submitSearch")
    public String getPoints(@RequestParam("type") String type, @RequestParam("docid") String docid, RedirectAttributes redirAttrs) throws SQLException
   {
        Secretary sec = new Secretary();
        String result = sec.getDocPoints(docid);
        redirAttrs.addFlashAttribute("success", result);
        return "redirect:/get-info";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/add-user")
    public String newUser(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "newUserForm";
    }

    @PostMapping("/submit-user")
    public String saveUser(@ModelAttribute("user") User user)
    {
        System.out.println(user.getUsername());
        service.register(user);
        return "redirect:/add-user";
    }
}