package by.pvt.controller.myDevices.given;


import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


public class WhenMyDevices extends Stage<WhenMyDevices> {

    @ScenarioState
    MockMvc mockMvc;

    @ProvidedScenarioState
    ModelAndView modelAndView;


    public WhenMyDevices execute() throws Exception {

        modelAndView = mockMvc
                .perform(get("/user/myDevices")
                        .with(user("login").password("password").roles("USER"))
                )
                .andReturn()
                .getModelAndView();
        return when();
    }
}