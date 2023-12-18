package EmployeesOf.G.G.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigure {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        http.csrf().disable();
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/abc").authenticated()
                )
                .authorizeHttpRequests((request) -> request.requestMatchers("/getInvoiceDetail/{id}", "/getInvoice/{id}", "addInvoice_Details", "/addInvoice", "/getUser/{id}", "/logoutUser", "/loginUser", "/addUser", "/forgetPassword", "/login", "/authenticates", "/addBank", "/getPayments"
                        , "/getPaid", "/getHistory", "/getPayandPayable", "/addHistory").permitAll());


        return http.build();

    }
}
