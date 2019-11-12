package by.pvt.controller.addDevice.given;

import by.pvt.pojo.cmd.DeviceCmd;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class WhenAddDevice extends Stage<WhenAddDevice> {

    @ScenarioState
    MockMvc mockMvc;

    @ExpectedScenarioState
    DeviceCmd device;

    @ProvidedScenarioState
    ModelAndView modelAndView;


    public WhenAddDevice execute() throws Exception {

        modelAndView = mockMvc
                .perform(post("/user/addDevice")
                        .with(user("login").password("password").roles("USER"))
                        .flashAttr("deviceCmd", device)
                )
                .andReturn()
                .getModelAndView();
        return when();
    }
}