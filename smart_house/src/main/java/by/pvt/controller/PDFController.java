package by.pvt.controller;


import by.pvt.pojo.cmd.ChartCmd;
import by.pvt.pojo.dto.DataChartDto;
import by.pvt.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("user/pdf")
public class PDFController {

    @Autowired
    SensorService sensorService;

    @GetMapping
    public ModelAndView pdf(@ModelAttribute ChartCmd chartCmd) {

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        DataChartDto dataChartDto = sensorService.getDataForPdf(chartCmd, userSpring.getUsername());
        return new ModelAndView("pdfDocument", "modelObject", dataChartDto);

    }


}
