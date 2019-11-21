package by.pvt.config;

import by.pvt.service.AuthenticationService;
import by.pvt.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.XmlViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("by.pvt")
@EnableScheduling
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    MessageService messageService;

    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        messageService.handleMessages();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthenticationService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**").addResourceLocations("/res/");
    }

    @Bean(name = "xmlViewResolver")
    public XmlViewResolver getXmlViewResolver() {
        XmlViewResolver xmlViewResolver = new XmlViewResolver();
        Resource resource = new ClassPathResource("pdf-config.xml");
        xmlViewResolver.setOrder(0);
        xmlViewResolver.setLocation(resource);
        return xmlViewResolver;
    }

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }



    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }


}
