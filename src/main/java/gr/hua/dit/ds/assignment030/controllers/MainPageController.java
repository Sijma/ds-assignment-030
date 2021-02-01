package gr.hua.dit.ds.assignment030.controllers;

import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Professors;
import gr.hua.dit.ds.assignment030.Entities.Users;
import gr.hua.dit.ds.assignment030.Services.DataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @Secured("ROLE_ADMIN")
    @PostMapping("/submit-user")
    public String saveUser(@ModelAttribute("user") Users users, RedirectAttributes redirAttrs)
    {
        service.registerUser(users);
        if (!(users.getAuthority().equals("ROLE_ADMIN")))
        {
            redirAttrs.addFlashAttribute("username",users.getUsername());
            redirAttrs.addFlashAttribute("role",users.getAuthority());
            return "redirect:/assign-user";
        }
        redirAttrs.addFlashAttribute("success","User "+ users.getUsername()+" Added Successfully.");
        return "redirect:/add-user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/assign-user")
    public String assignUser(ModelMap model, @ModelAttribute("username") Object usernameAttribute, @ModelAttribute("role") Object roleAttribute)
    {
        String role = roleAttribute.toString();
        String username = usernameAttribute.toString();
        model.addAttribute("fixedUsername", username);

        if (role.equals("ROLE_PROF"))
        {
            Professors prof = new Professors();
            prof.setUser(service.getUser(username));
            model.addAttribute("Prof", prof);
            return "assignProf";
        }
        else
        {
            Candidates candidate = new Candidates();
            candidate.setUser(service.getUser(username));
            model.addAttribute("Candidate", candidate);
            return "assignCandidate";
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/assign-prof-submit")
    public String submitProf(@ModelAttribute("Prof") Professors professor, RedirectAttributes redirAttrs)
    {
        service.registerProf(professor);
        redirAttrs.addFlashAttribute("success","User "+ professor.getUser().getUsername()+" Added Successfully.");
        return "redirect:/add-user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/assign-can-submit")
    public String submitCan(@ModelAttribute("Candidate") Candidates candidate, RedirectAttributes redirAttrs)
    {
        service.registerCan(candidate);
        redirAttrs.addFlashAttribute("success","User "+ candidate.getUser().getUsername()+" Added Successfully.");
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

    @RequestMapping("/edit/{username}")
    public ModelAndView showEditProductForm(@PathVariable(name = "username") String username)
    {
        ModelAndView mav = new ModelAndView("edit-user");

        Users user = service.getUser(username);
        mav.addObject("user", user);

        return mav;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/deleteUser/{username}")
    public String deleteUser(@PathVariable(name = "username") String username)
    {
        service.deleteUser(username);
        return "redirect:/list-users";
    }
}