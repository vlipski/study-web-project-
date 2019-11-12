package by.pvt.controller.myDevices.given;

import by.pvt.pojo.dto.MyDevicesDto;
import by.pvt.pojo.dto.ShowDevicesDto;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;


public class ThenMyDevices extends Stage<ThenMyDevices> {


    @ExpectedScenarioState
    ModelAndView modelAndView;

    public ThenMyDevices showDevices() {
        assertNotNull(modelAndView);
        assertEquals("myDevices", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("devices"));
        MyDevicesDto devicesDto = (MyDevicesDto) modelAndView.getModel().get("devices");
        ShowDevicesDto deviceDto = devicesDto.getShowDevicesDtoList().get(0);
        assertEquals("cond001", deviceDto.getSerialNumber());
        return self();
    }

}