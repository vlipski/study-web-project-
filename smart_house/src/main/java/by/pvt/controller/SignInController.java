package by.pvt.controller;

import by.pvt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    UserService userService;

    @GetMapping
    public String showFormSignIn() {
        return "signIn";
    }

}
