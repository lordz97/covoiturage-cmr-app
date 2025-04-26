package com.blablacar.backend.Service;

import java.time.LocalDate;

import com.blablacar.backend.Exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.blablacar.backend.Model.Role;
import com.blablacar.backend.Model.Utilisateur;
import com.blablacar.backend.Repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, BCryptPasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur inscrire(Utilisateur utilisateur) throws Exception {
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()) != null) {
            throw new UserAlreadyExistsException("Email déjà utilisé !");
        }
        utilisateur.setMdp(passwordEncoder.encode(utilisateur.getMdp()));
        utilisateur.setDateInscription(LocalDate.now());
        utilisateur.setRole(Role.PASSAGER);
        return utilisateurRepository.save(utilisateur);
    }

}
