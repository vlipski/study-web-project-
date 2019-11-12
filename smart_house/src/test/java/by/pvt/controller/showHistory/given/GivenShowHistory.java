package by.pvt.controller.showHistory.given;


import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class GivenShowHistory extends Stage<GivenShowHistory> {

    @ScenarioState
    WebApplicationContext webApplicationContext;

    @ScenarioState
    MockMvc mockMvc;



    @As("")
    public GivenShowHistory runWith(WebApplicationContext wac) {
        webApplicationContext = wac;
        return self();
    }

    public GivenShowHistory showSensors() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
        return self();
    }
}
