package gr.hua.dit.ds.assignment030.controllers;

import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Users;
import gr.hua.dit.ds.assignment030.Services.DataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

    /*@Secured({"ROLE_ADMIN", "ROLE_SECRETARY"})
    @PostMapping("/submitSearch")
    public String getPoints(@RequestParam("type") String type, @RequestParam("docid") String docid, RedirectAttributes redirAttrs) throws SQLException
    {
        Secretary sec = new Secretary();
        String result = sec.getDocPoints(docid);
        redirAttrs.addFlashAttribute("success", result);
        return "redirect:/get-info";
    }*/

    @Secured("ROLE_ADMIN")
    @GetMapping("/add-user")
    public String newUser(Model model)
    {
        Users users = new Users();
        model.addAttribute("user", users);
        return "newUserForm";
    }

    @PostMapping("/submit-user")
    public String saveUser(@ModelAttribute("user") Users users, RedirectAttributes redirAttrs)
    {
        service.register(users);
        redirAttrs.addFlashAttribute("success","Users "+ users.getUsername()+" Added Successfully.");
        return "redirect:/add-user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/list-candidates")
    public String listCandidates(Model model)
    {
        List<Candidates> listCandidates = service.listAllCandidates("");
        model.addAttribute("listCandidates", listCandidates);

        return "listAllCandidates";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/list-users")
    public String listUsers(Model model)
    {
        List<Users> listUsers = service.listAllUsers("");
        model.addAttribute("listUsers", listUsers);

        return "listAllUsers";
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable(name = "username") String username)
    {
        service.deleteUser(username);
        return "redirect:/list-users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/assignUser/{username}")
    public String assignUser(@PathVariable(name = "username") String username)
    {
        service.assignUser(username);
        return "redirect:/list-users";
    }
}