package by.pvt.controller.showHistory;


import by.pvt.config.TestConfiguration;
import by.pvt.controller.showHistory.given.GivenShowHistory;
import by.pvt.controller.showHistory.given.ThenShowHistory;
import by.pvt.controller.showHistory.given.WhenShowHistory;
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
public class ShowHistoryControllerTest extends ScenarioTest<GivenShowHistory, WhenShowHistory, ThenShowHistory> {

    @Autowired
    WebApplicationContext webApplicationContext;



    @Test
    @Sql(value = "/sql/show_sensors_create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/show_sensors_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void showDevicesAndSensors() throws Exception {
        given().runWith(webApplicationContext).showSensors();
        when().execute(1L);
        then().showDevicesAndSensors();
    }
}
