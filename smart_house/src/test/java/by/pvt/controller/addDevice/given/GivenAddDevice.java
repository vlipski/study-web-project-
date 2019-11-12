package by.pvt.controller.addDevice.given;

import by.pvt.pojo.cmd.DeviceCmd;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class GivenAddDevice extends Stage<GivenAddDevice> {

    @ScenarioState
    WebApplicationContext webApplicationContext;

    @ScenarioState
    MockMvc mockMvc;

    @ProvidedScenarioState
    DeviceCmd device;

    @As("")
    public GivenAddDevice runWith(WebApplicationContext wac) {
        webApplicationContext = wac;
        return self();
    }

    public GivenAddDevice createDevice(DeviceCmd device) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
        this.device = device;
        return self();
    }
}
