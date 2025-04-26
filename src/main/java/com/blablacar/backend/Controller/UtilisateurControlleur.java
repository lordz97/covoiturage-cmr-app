package com.blablacar.backend.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blablacar.backend.Model.Utilisateur;
import com.blablacar.backend.Service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurControlleur {
    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurControlleur(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    @PostMapping("/inscription")
    public ResponseEntity<?> inscription(@RequestBody Utilisateur utilisateur) throws Exception {
        return ResponseEntity.ok(utilisateurService.inscrire(utilisateur));
    }

    /* /connexion et /deconnexion sont maintenant gérés par Spring Security.
       Si vous souhaitez quand même les garder pour des raisons “front”,
       renvoyez simplement 200 OK : */
    @PostMapping("/connexion")
    public ResponseEntity<?> connexion() {        // pas de body nécessaire
        return ResponseEntity.ok("Connecté");      // la session est déjà créée
    }

    @PostMapping("/deconnexion")
    public ResponseEntity<?> deconnexion() {
        return ResponseEntity.ok("Déconnecté");
    }

}
