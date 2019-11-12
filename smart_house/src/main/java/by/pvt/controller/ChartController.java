package by.pvt.controller;


import by.pvt.pojo.cmd.ChartCmd;
import by.pvt.pojo.dto.DataChartDto;
import by.pvt.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/chart")
public class ChartController {

    @Autowired
    SensorService sensorService;


    @GetMapping
    public String showChart(@ModelAttribute ChartCmd chartCmd, Model model) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DataChartDto dataChartDto = sensorService.getDataForChart(chartCmd, userSpring.getUsername());
        model.addAttribute("dataChart", dataChartDto);
        return "chart";
    }

}
