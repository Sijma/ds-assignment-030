package gr.hua.dit.ds.dsassignment030.controllers;

import gr.hua.dit.ds.dsassignment030.Users.Secretary;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
public class MainPageController {

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
    @PostMapping(value="/submitSearch")
    public String getPoints(@RequestParam("type") String type, @RequestParam("docid") String docid, RedirectAttributes redirAttrs) throws SQLException
    {
        Secretary sec = new Secretary();
        String result = sec.getDocPoints(docid);
        redirAttrs.addFlashAttribute("success", result);
        return "redirect:/get-info";
    }
}