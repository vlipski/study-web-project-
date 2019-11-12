package by.pvt.controller.addDevice.given;

import by.pvt.pojo.dto.MyDevicesDto;
import by.pvt.pojo.dto.ShowDevicesDto;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;


public class ThenAddDevice extends Stage<ThenAddDevice> {


    @ExpectedScenarioState
    ModelAndView modelAndView;

    public ThenAddDevice addDeviceValid() {
        assertNotNull(modelAndView);
        assertEquals("myDevices", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("devices"));
        MyDevicesDto devicesDto = (MyDevicesDto) modelAndView.getModel().get("devices");
        ShowDevicesDto deviceDto = devicesDto.getShowDevicesDtoList().get(0);
        assertEquals("cond001", deviceDto.getSerialNumber());
        return self();
    }


    public ThenAddDevice addDeviceInvalid() {
        assertNotNull(modelAndView);
        assertEquals("addDevice", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().containsKey("org.springframework.validation.BindingResult.deviceCdm"));
        BeanPropertyBindingResult result = (BeanPropertyBindingResult) modelAndView
                .getModel()
                .get("org.springframework.validation.BindingResult.deviceCdm");
        assertEquals(2, result.getModel().size());
        return self();
    }

}