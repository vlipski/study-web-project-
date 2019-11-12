package by.pvt.controller.myDevices.given;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class GivenMyDevices extends Stage<GivenMyDevices> {

    @ScenarioState
    WebApplicationContext webApplicationContext;

    @ScenarioState
    MockMvc mockMvc;


    @As("")
    public GivenMyDevices runWith(WebApplicationContext wac) {
        webApplicationContext = wac;
        return self();
    }

    public GivenMyDevices showDevices() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
        return self();
    }
}
