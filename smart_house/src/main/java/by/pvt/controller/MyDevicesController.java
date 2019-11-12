package by.pvt.controller;


import by.pvt.io.IOService;
import by.pvt.pojo.cmd.ChangeCmd;
import by.pvt.service.DeviceService;
import by.pvt.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/myDevices")
public class MyDevicesController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    SensorService sensorService;

    @Autowired
    IOService ioService;

    @GetMapping
    public String showDevices(Model model) {
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("devices",
                deviceService.getDevicesByUserName(userSpring.getUsername()));
        return "myDevices";
    }

    @GetMapping("/{id}")
    public String showValues(@PathVariable Long id, Model model) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("devices", sensorService.getValuesSensors(id, userSpring.getUsername()));
        return "myDevices";
    }

    @GetMapping("/change")
    public String change(@ModelAttribute ChangeCmd changeCmd, Model model) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("devices", sensorService.send(changeCmd, userSpring.getUsername()));
        return "myDevices";



    }

}
