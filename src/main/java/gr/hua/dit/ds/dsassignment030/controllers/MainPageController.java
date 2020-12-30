/*
package gr.hua.dit.ds.dsassignment030.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController
{
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/error")
    public String error()
    {
        return "error";
    }
}
*/

package gr.hua.dit.ds.dsassignment030.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/")
    public String showIndex(){
        return "index";
    }

    @GetMapping("/about")
    public String showAbout(){
        return "about";
    }

    @GetMapping("/logout")
    public void logout() { }
}