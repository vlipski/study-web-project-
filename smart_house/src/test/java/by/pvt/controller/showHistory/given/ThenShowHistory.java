package by.pvt.controller.showHistory.given;

import by.pvt.pojo.dto.MyDevicesDto;
import by.pvt.pojo.dto.ShowDevicesDto;
import by.pvt.pojo.dto.ShowHistoryDto;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;


public class ThenShowHistory extends Stage<ThenShowHistory> {


    @ExpectedScenarioState
    ModelAndView modelAndView;

    public ThenShowHistory showDevicesAndSensors() {
        assertNotNull(modelAndView);
        assertEquals("showHistory", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("devices"));
        ShowHistoryDto showHistoryDto = (ShowHistoryDto) modelAndView.getModel().get("devices");
        ShowDevicesDto deviceDto = showHistoryDto.getShowDevicesDtoList().get(0);
        assertEquals("cond001", deviceDto.getSerialNumber());
        assertEquals(2,showHistoryDto.getShowSensorsDtoList().size());
        return self();
    }

}