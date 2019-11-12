package by.pvt.controller.myDevices;


import by.pvt.config.TestConfiguration;
import by.pvt.controller.myDevices.given.GivenMyDevices;
import by.pvt.controller.myDevices.given.ThenMyDevices;
import by.pvt.controller.myDevices.given.WhenMyDevices;
import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = TestConfiguration.class)
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class MyDevicesControllerTest extends ScenarioTest<GivenMyDevices, WhenMyDevices, ThenMyDevices> {

    @Autowired
    WebApplicationContext webApplicationContext;



    @Test
    @Sql(value = "/sql/show_devices_create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/device_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void showDeviceOk() throws Exception {
        given().runWith(webApplicationContext).showDevices();
        when().execute();
        then().showDevices();
    }

}
