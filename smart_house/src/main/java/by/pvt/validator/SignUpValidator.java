package by.pvt.validator;

import by.pvt.pojo.cmd.UserSignUpCmd;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpValidator.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserSignUpCmd userSignUpCmd = (UserSignUpCmd) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "name",
                "signUp.name.empty",
                "Username must not be empty");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "password",
                "signUp.password.empty",
                "Password must not be empty");
        if (userSignUpCmd.getPassword().length() < 5) {
            errors.rejectValue(
                    "password",
                    "signUp.password.minSize",
                    "Password must be more than 5 characters");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "login",
                "signUp.login.empty",
                "Login must not be empty");
        if (userSignUpCmd.getLogin().length() < 5) {
            errors.rejectValue(
                    "login",
                    "signUp.login.minSize",
                    "Login must be more than 5 characters");
        }



        if (userSignUpCmd.getRepeatPassword() == null || !(userSignUpCmd.getPassword().equals(userSignUpCmd.getRepeatPassword()))) {
            errors.rejectValue(
                    "repeatPassword",
                    "signUp.repeatPassword.noMatch",
                    "Passwords don't match");
        }
    }
}
