package by.pvt.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(authenticationService)
        .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**")
                .hasRole("USER")
                .antMatchers(HttpMethod.POST, "/user/**")
                .hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/signIn")
                .loginProcessingUrl("/loginCheck")
                .defaultSuccessUrl("/user/myDevices")
                .failureUrl("/signIn?error=true")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionFixation().none();
    }


}