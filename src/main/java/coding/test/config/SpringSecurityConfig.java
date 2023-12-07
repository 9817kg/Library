package coding.test.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import coding.test.ouath2.OAuth2Service;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {
    @Autowired
    private final OAuth2Service oAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/status", "/join", "/auth/join", "/main","/login","/","/category/**").permitAll()
                                .requestMatchers("/findEmail", "/resultFindEmail", "/findPw").permitAll()
                                .requestMatchers("/book/bookDetail/**","/api/**").permitAll()
                                
                               
                                
                               
                                .requestMatchers("/css/**").permitAll()
                                
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/movie/**").permitAll()
                                .requestMatchers("/uploadImg/**").permitAll()
                                
                                
                                
                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                //.requestMatchers("/my/**").hasAuthority("ROLE_USER")
                                .anyRequest().authenticated()
                )
                .formLogin(login -> login
                                .loginPage("/login")
                                .loginProcessingUrl("/login-process")
                                .usernameParameter("email")
                                .passwordParameter("password")
                               
                                .defaultSuccessUrl("/", false) 
                                .successHandler((request, response, authentication) -> {
                                   
                                    if (authentication != null && authentication.getAuthorities().stream()
                                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                        response.sendRedirect("/admin/admin");
                                    } else {
                                    	request.getSession().setAttribute("message", "로그인 성공!");
                                        response.sendRedirect("/main");
                                    }
                                })


                )
                .oauth2Login(oauth2Configurer -> oauth2Configurer
                                .loginPage("/login")

                                .defaultSuccessUrl("/main", true) 
                                .userInfoEndpoint()
                       
                                .userService(oAuth2UserService)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .invalidateHttpSession(true));
     

        return http.build();
    }
   
    
}