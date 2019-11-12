package by.pvt.controller.signUp.given;

import by.pvt.pojo.cmd.UserSignUpCmd;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class WhenSignUp extends Stage<WhenSignUp> {

    @ScenarioState
    MockMvc mockMvc;

    @ExpectedScenarioState
    UserSignUpCmd user;

    @ProvidedScenarioState
    ModelAndView modelAndView;


    public WhenSignUp execute() throws Exception {

        modelAndView = mockMvc
                .perform(post("/signUp").flashAttr("userSignUpCmd", user))
                .andReturn().getModelAndView();
        return when();
    }
}