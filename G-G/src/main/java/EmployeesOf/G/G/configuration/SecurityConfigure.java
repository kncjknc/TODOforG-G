package EmployeesOf.G.G.configuration;


import EmployeesOf.G.G.services.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigure {

    private final JwtFilter jwtFilter;

    public SecurityConfigure(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    UserDetailsService userDeailService() {
        return new UserInfoService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((c->c.disable()))
                .authorizeHttpRequests((requests -> requests
                .requestMatchers("/abc", "/addUser", "/getUser/{id}").authenticated()
                .requestMatchers("/addEmployee"
                        , "/loginUsers","/getGreaterSalary/{salary}"
                        ,"/getAll", "/getUser/{name}"
                        ,"/getAllDepartmentAces"
                        ,"/getEmployeesCount","/user/{id}","/getEmployee/{id}","/department/{id}").permitAll()
                )).sessionManagement((s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)));
        http.authenticationProvider(authenticationProvider()).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic();
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDeailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
