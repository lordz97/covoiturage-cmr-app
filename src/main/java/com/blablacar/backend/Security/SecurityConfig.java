package com.blablacar.backend.Security;

import com.blablacar.backend.Repository.UtilisateurRepository;
import com.blablacar.backend.Model.Utilisateur;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** Spring récupère le user dans la DB et compare les mots de passe encodés */
    @Bean
    public UserDetailsService userDetailsService(UtilisateurRepository repo) {
        return email -> {
            Utilisateur u = repo.findByEmail(email);
            if (u == null) throw new UsernameNotFoundException("Utilisateur introuvable");
            return User.withUsername(u.getEmail())
                    .password(u.getMdp())
                    .roles(u.getRole().name())
                    .build();
        };
    }

    /** Chaîne de filtres */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())           // plus simple pour Postman
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/utilisateurs/inscription",
                                "/api/utilisateurs/connexion").permitAll()
                        .anyRequest().authenticated())
                // On laisse Spring créer la session (cookie JSESSIONID)
                .formLogin(form -> form
                        .loginProcessingUrl("/api/utilisateurs/connexion")      // POST JSON
                        .successHandler((req,res,auth) -> res.setStatus(HttpServletResponse.SC_OK))
                        .failureHandler((req,res,ex) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                                "Email ou mot de passe invalide")))
                .logout(logout -> logout
                        .logoutUrl("/api/utilisateurs/deconnexion")
                        .logoutSuccessHandler((req,res,auth) -> res.setStatus(HttpServletResponse.SC_OK)))
                .sessionManagement(Customizer.withDefaults());

        return http.build();
    }
}
