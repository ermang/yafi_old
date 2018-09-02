package egcom.yafi.packy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@EnableWebSecurity
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public WebConfiguration(DataSource dataSource) {
      super();
      this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/topic", "/thread", "/login").hasAnyRole("USER", "ADMIN")
            .anyRequest().permitAll()
            .and()
            .httpBasic()
            .and()
            .csrf().disable(); //TODO: FIXME


//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//                .jdbcAuthentication()//
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(users.username("user").password("user").roles("USER"))
//                .withUser(users.username("admin").password("password").roles("USER","ADMIN"));

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery("select username, role FROM user where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
