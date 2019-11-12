package by.pvt.controller.signUp;

import by.pvt.config.TestConfiguration;
import by.pvt.controller.signUp.given.GivenSignUp;
import by.pvt.controller.signUp.given.ThenSignUp;
import by.pvt.controller.signUp.given.WhenSignUp;
import by.pvt.pojo.cmd.UserSignUpCmd;
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
public class SignUpControllerTest extends ScenarioTest<GivenSignUp, WhenSignUp, ThenSignUp> {

    @Autowired
    WebApplicationContext webApplicationContext;



    @Test
    @Sql(value = "/sql/user_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void singUpOk() throws Exception {
        given().runWith(webApplicationContext).before(new UserSignUpCmd("vitaly","login", "password", "password"));
        when().execute();
        then().result("signIn");
    }

    @Test
    @Sql(value = "/sql/user_create.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/user_delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void singUpError() throws Exception{
        given().runWith(webApplicationContext).before(new UserSignUpCmd("vitaly","login", "password", "password"));
        when().execute();
        then().result("signUp");
    }

}
