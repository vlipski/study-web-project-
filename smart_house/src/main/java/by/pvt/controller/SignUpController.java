package by.pvt.controller;



import by.pvt.pojo.cmd.UserSignUpCmd;
import by.pvt.service.UserService;
import by.pvt.validator.SignUpValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private static final Logger log = Logger.getLogger(SignUpController.class);

    @Autowired
    UserService userService;

    @Autowired
    SignUpValidator validator;

    @GetMapping
    public String showFormSignUp() {
        return "signUp";
    }

    @PostMapping
    public String signUp(@ModelAttribute("userSignUpCmd") UserSignUpCmd userSignUpCmd,
                         Model model,
                         BindingResult result) {

        validator.validate(userSignUpCmd, result);

        if (result.hasErrors()) {
            log.info("invalid data: " + userSignUpCmd );
            model.addAttribute("userSignUpCmd", userSignUpCmd);
            return "signUp";
        }

        String error = userService.creatUser(userSignUpCmd);
        if(error != null) {
            log.info("invalid data: " + userSignUpCmd );
            model.addAttribute("userSignUpCmd", userSignUpCmd);
            model.addAttribute("error",error);
            return "signUp";
        }

        return "signIn";
    }
}
