package gr.hua.dit.ds.assignment030.controllers;

import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Professors;
import gr.hua.dit.ds.assignment030.Entities.Users;
import gr.hua.dit.ds.assignment030.Services.DataServices;
import gr.hua.dit.ds.assignment030.config.AppSecurityConfig;
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

    @Secured("ROLE_ADMIN")
    @GetMapping("/add-user")
    public String newUser(Model model, @ModelAttribute("userRedirect") Object flashAttribute)
    {
        Users user = new Users();
        model.addAttribute("user", user);
        model.addAttribute("passwordConfirm", "");
        return "newUserForm";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/submit-user")
    public String saveUser(@ModelAttribute("user") Users users, @RequestParam String confPassword, RedirectAttributes redirAttrs)
    {
        if (users.getAuthority().equals(""))
        {
            redirAttrs.addFlashAttribute("invalidRole", true);
            return "redirect:/add-user";
        }
        if (confPassword.equals(users.getPassword()) && !service.usernameExists(users.getUsername()))
        {
            service.registerUser(users);
        }
        else if (!confPassword.equals(users.getPassword()))
        {
            redirAttrs.addFlashAttribute("invalidPass", true);
            return "redirect:/add-user";
        }
        else
        {
            redirAttrs.addFlashAttribute("invalidName", true);
            return "redirect:/add-user";
        }

        if (!(users.getAuthority().equals("ROLE_ADMIN")))
        {
            redirAttrs.addFlashAttribute("username",users.getUsername());
            redirAttrs.addFlashAttribute("role",users.getAuthority());
            redirAttrs.addFlashAttribute("idNotExist",false);
            return "redirect:/assign-user";
        }
        redirAttrs.addFlashAttribute("success","User "+ users.getUsername()+" Added Successfully.");
        return "redirect:/add-user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") Users user, RedirectAttributes redirAttrs)
    {
        service.updateUser(user);
        return "redirect:/list-users";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/assign-user")
    public String assignUser(ModelMap model, @ModelAttribute("username") Object usernameAttribute, @ModelAttribute("role") Object roleAttribute, @ModelAttribute("idNotExist") Object idExistAttribute)
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
            //model.addAttribute("idNotExist", idExistAttribute);
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
        if (!service.superIdExists(candidate.getProfessor().getPersonellID()))
        {
            redirAttrs.addFlashAttribute("idNotExist",true);
            redirAttrs.addFlashAttribute("username",candidate.getUser().getUsername());
            redirAttrs.addFlashAttribute("role","ROLE_CANDIDATE");
            return "redirect:/assign-user";
        }
        service.registerCan(candidate);
        redirAttrs.addFlashAttribute("success","User "+ candidate.getUser().getUsername()+" Added Successfully.");
        return "redirect:/add-user";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SECRETARY"})
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
    @GetMapping("/editUser-{username}")
    public ModelAndView showEditUserForm(@PathVariable(name = "username") String username)
    {
        ModelAndView mav = new ModelAndView("edit-user");
        Users user = service.getUser(username);
        mav.addObject("user", user);
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/reset-pass-{username}")
    public String resetPassword(Model model, @PathVariable(name = "username") String username)
    {
        model.addAttribute("username", username);
        return "resetPassword";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/submit-password")
    public String submitNewPassword(@RequestParam String username ,@RequestParam String password ,@RequestParam String confPassword, RedirectAttributes redirAttrs)
    {
        if (password.equals(confPassword))
        {
            Users changePassUser = service.getUser(username);
            service.changePass(changePassUser, password);
            service.updateUser(changePassUser);
            redirAttrs.addFlashAttribute("success","User "+ username +" edited successfully.");
            return "redirect:editUser-"+username;
        }
        redirAttrs.addFlashAttribute("invalidPass", true);
        return "redirect:/reset-pass-"+username;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editCan-{candidateID}")
    public ModelAndView showEditCandidateForm(@PathVariable(name = "candidateID") String canId)
    {
        ModelAndView mav = new ModelAndView("editCandidate");
        Candidates candidate = service.getCandidate(canId);
        mav.addObject("candidate", candidate);
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/update-candidate")
    public String updateCan(@ModelAttribute("candidate") Candidates candidate, RedirectAttributes redirAttrs)
    {
        service.updateCan(candidate, candidate.getCandidateId());
        return "redirect:/list-candidates";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/deleteUser-{username}")
    public String deleteUser(@PathVariable(name = "username") String username)
    {
        service.deleteUser(username);
        return "redirect:/list-users";
    }

    @Secured("ROLE_PROF")
    @GetMapping("/view-super-candidates")
    public String viewSuperCan(Model model)
    {
        String sessionUsername = AppSecurityConfig.getSessionUsername();
        System.out.println("Session Username: "+sessionUsername);
        Professors prof = service.getUser(sessionUsername).getProfessor();
        List<Candidates> listCans = prof.getCandidatesList();

        model.addAttribute("listCans", listCans);

        return "listSuperCandidates";
    }

    @Secured("ROLE_PROF")
    @GetMapping("/set-targets-{username}")
    public ModelAndView setTargets(@PathVariable(name = "username") String username)
    {
        ModelAndView mav = new ModelAndView("setTargets");

        Candidates candidate = service.getUser(username).getCandidate();
        mav.addObject("Candidate", candidate);

        return mav;
    }

    @Secured("ROLE_PROF")
    @PostMapping("/assign-targets")
    public String assignTargets(@ModelAttribute("Candidate") Candidates candidate, RedirectAttributes redirAttrs)
    {
        service.updateCan(candidate, candidate.getCandidateId());
        redirAttrs.addFlashAttribute("success","User "+ candidate.getUser().getUsername()+" targets updated successfully");
        return "redirect:/view-super-candidates";
    }

    @Secured("ROLE_CANDIDATE")
    @GetMapping("/view-progress")
    public String viewProgress(Model model)
    {
        String sessionUsername = AppSecurityConfig.getSessionUsername();
        Candidates candidate = service.getUser(sessionUsername).getCandidate();

        model.addAttribute("candidate", candidate);

        return "viewProgress";
    }

    @Secured("ROLE_SECRETARY")
    @GetMapping("/add-points-{id}")
    public ModelAndView addPoints(@PathVariable(name = "id") String id)
    {
        ModelAndView mav = new ModelAndView("addPoints");

        Candidates candidate = service.getCandidate(id);
        mav.addObject("Candidate", candidate);

        return mav;
    }

    @Secured("ROLE_SECRETARY")
    @PostMapping("/submit-points")
    public String submitPoints(@ModelAttribute("Candidate") Candidates candidate, RedirectAttributes redirAttrs)
    {
        service.updateCan(candidate, candidate.getCandidateId());
        redirAttrs.addFlashAttribute("success","User "+ candidate.getUser().getUsername()+" targets updated successfully");
        return "redirect:/list-candidates";
    }
}