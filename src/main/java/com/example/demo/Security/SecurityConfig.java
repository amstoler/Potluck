
package com.example.demo.Security;

import com.example.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUDS(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/user/register").permitAll()
                .antMatchers("/user**").hasAuthority("USER")
                //.antMatchers("/addJob", "/" ).hasAuthority("ADMIN")
                //.antMatchers("/matchedJobs", "/available").access("hasAuthority('APPLICANT') or hasAuthority('RECRUITER')")
                //.antMatchers("/completedResume", "/coverLetter").access("hasAuthority('APPLICANT') or hasAuthority('EMPLOYER')")
                .anyRequest().authenticated()
                .and()
                .formLogin().successForwardUrl("/user/index")
                .and()

                //Displays 'you have been logged out' message on login form when a user logs out (default login form). Change this
                //to logout?logout if you are using a custom form.
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll().logoutSuccessUrl("/");

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password").authorities("USER");

        //Gets information from the database. See the code comments in the SSUDS class for user details. Haha.
        auth.userDetailsService(userDetailsServiceBean());
    }
}
