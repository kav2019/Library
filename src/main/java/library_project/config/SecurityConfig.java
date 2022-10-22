package library_project.config;

import library_project.security.AuthProvaiderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@ComponentScan("library_project")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProvaiderImpl authProvaider;

    @Autowired
    public SecurityConfig(AuthProvaiderImpl authProvaider) {
        this.authProvaider = authProvaider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvaider);
    }

    //    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authenticationProvider(authProvaider);
//        http.authorizeRequests()
//                .antMatchers("/library").access("hasRole('USER')")
//                .and().formLogin().defaultSuccessUrl("/", true);
//
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService()  {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build());
//        return manager;
//    }

}
