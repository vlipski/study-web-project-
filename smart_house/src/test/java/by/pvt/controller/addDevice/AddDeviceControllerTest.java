package by.pvt.controller.addDevice;


import by.pvt.config.TestConfiguration;
import by.pvt.controller.addDevice.given.GivenAddDevice;
import by.pvt.controller.addDevice.given.ThenAddDevice;
import by.pvt.controller.addDevice.given.WhenAddDevice;
import by.pvt.pojo.cmd.DeviceCmd;
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
public class AddDeviceControllerTest extends ScenarioTest<GivenAddDevice, WhenAddDevice, ThenAddDevice> {

    @Autowired
    WebApplicationContext webApplicationContext;



    @Test
    @Sql(value = "/sql/device_create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/device_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addDeviceValid() throws Exception {
        given().runWith(webApplicationContext).createDevice(new DeviceCmd("my_conditioner","cond001", "1111"));
        when().execute();
        then().addDeviceValid();
    }

    @Test
    @Sql(value = "/sql/device_create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/device_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addDeviceInvalid() throws Exception{
        given().runWith(webApplicationContext).createDevice(new DeviceCmd("my_conditioner","", ""));
        when().execute();
        then().addDeviceInvalid();
    }
}
