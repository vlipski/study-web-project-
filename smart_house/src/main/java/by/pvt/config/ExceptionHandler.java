package by.pvt.config;

import by.pvt.controller.AddDeviceController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = Logger.getLogger(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {

        log.error(e);
        e.printStackTrace();
        return new ModelAndView("home");
    }
}
