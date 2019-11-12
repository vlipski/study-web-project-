package by.pvt.controller;


import by.pvt.pojo.cmd.DeviceCmd;
import by.pvt.service.DeviceService;
import by.pvt.validator.AddDeviceValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user/addDevice")
public class AddDeviceController {

    private static final Logger log = Logger.getLogger(AddDeviceController.class);

    @Autowired
    DeviceService deviceService;

    @Autowired
    AddDeviceValidator validator;

    @GetMapping
    public String showFormAddDevice() {
        return "addDevice";
    }

    @PostMapping
    public String addDevice(@ModelAttribute("deviceCmd") DeviceCmd deviceCmd,
                            Model model,
                            BindingResult result) {

        validator.validate(deviceCmd, result);
        if (result.hasErrors()) {
            log.info("invalid data: " + deviceCmd);
            model.addAttribute("deviceCdm", deviceCmd);
            return "addDevice";
        }

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal();


        String error = deviceService.addDevice(deviceCmd, userSpring.getUsername());
        if (error != null) {
            log.info("invalid data: " + deviceCmd);
            model.addAttribute("deviceCmd", deviceCmd);
            model.addAttribute("error", error);
            return "addDevice";
        }

        model.addAttribute("devices",
                deviceService.getDevicesByUserName(userSpring.getUsername()));
        return "myDevices";
    }
}
