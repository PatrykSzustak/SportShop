package solshop.user.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import solshop.user.service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
//        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http

                .formLogin()
                    .loginPage("/index")
                    .failureForwardUrl("/index")
                .and()
                    .logout()
                    .logoutSuccessUrl("/index")
                .and()
                    .authorizeRequests().antMatchers("/skleptest","/skleptest2").permitAll();

                 /*   .antMatchers("/skleptest").hasRole("ADMIN")
                    .antMatchers("/skleptest2").hasRole("USER");*/

//                .authorizeRequests()
//                .antMatchers("/skleptest").hasRole("ADMIN")
//                .antMatchers("/skleptest2").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/index")
//                .permitAll();
////                .defaultSuccessUrl("/skleptest3");
////                .failureUrl("/index");

    }


/*    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }*/


}

