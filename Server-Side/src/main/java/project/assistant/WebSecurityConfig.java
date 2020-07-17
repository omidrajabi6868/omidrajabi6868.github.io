package project.assistant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    public WebSecurityConfig(){
        super();
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/Patient/**").access("hasRole('PATIENT')")
                .antMatchers("/Physician/**").access("hasRole('PHYSICIAN')")
                .and()
                .formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/Account/Login")
                .failureUrl("/Account/Login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().rememberMe()
                .key("uniqueAndSecret")
                .rememberMeParameter("remember") // it is name of checkbox at login page
                .rememberMeCookieName("rememberlogin") // it is name of the cookie
                .tokenValiditySeconds(86400) // remember for number of seconds
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/Account/Login")
                .and().exceptionHandling().accessDeniedPage("/Account/AccessDenied")
                .and().csrf();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**","/views/**");


    }


}
