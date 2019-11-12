package by.pvt.controller.showHistory.given;

import by.pvt.pojo.cmd.DeviceCmd;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class WhenShowHistory extends Stage<WhenShowHistory> {

    @ScenarioState
    MockMvc mockMvc;

    @ProvidedScenarioState
    ModelAndView modelAndView;


    public WhenShowHistory execute(Long id) throws Exception {

        modelAndView = mockMvc
                .perform(get("/user/showHistory/{id}", id)
                        .with(user("login").password("password").roles("USER"))
                )
                .andReturn()
                .getModelAndView();
        return when();
    }
}