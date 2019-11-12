package by.pvt.controller.signUp.given;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;


public class ThenSignUp extends Stage<ThenSignUp> {



    @ExpectedScenarioState
    ModelAndView modelAndView;

    public ThenSignUp result(String viewName) {
        assertNotNull(modelAndView);
        assertEquals(viewName, modelAndView.getViewName());
        return self();
    }
}