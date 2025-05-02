package com.blablacar.backend.Security;

import com.blablacar.backend.Model.Utilisateur;
import com.blablacar.backend.Repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        return User.withUsername(utilisateur.getEmail())
                .password(utilisateur.getMdp())
                .roles(utilisateur.getRole().name())
                .build();
    }
}
