package by.pvt.controller;


import by.pvt.service.DeviceService;
import by.pvt.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/showHistory")
public class ShowHistoryController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    SensorService sensorService;

    @GetMapping
    public String showDevices(Model model) {
        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("devices",
                deviceService.getShowHistoryDto(userSpring.getUsername()));
        return "showHistory";
    }


    @GetMapping("/{id}")
    public String showSensors(@PathVariable Long id, Model model) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("devices", sensorService.getShowHistoryDto(id, userSpring.getUsername()));
        return "showHistory";
    }

}
