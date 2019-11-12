package by.pvt.controller.signUp.given;

import by.pvt.pojo.cmd.UserSignUpCmd;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class GivenSignUp extends Stage<GivenSignUp> {

    @ScenarioState
    WebApplicationContext webApplicationContext;

    @ScenarioState
    MockMvc mockMvc;

    @ProvidedScenarioState
    UserSignUpCmd user;

    @As("web application")
    public GivenSignUp runWith(WebApplicationContext wac) {
        webApplicationContext = wac;
        return self();
    }

    public GivenSignUp before(UserSignUpCmd user) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.user = user;
        return self();
    }
}
