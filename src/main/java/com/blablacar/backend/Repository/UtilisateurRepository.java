package com.blablacar.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blablacar.backend.Model.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByEmail(String email);
    Utilisateur findByNom(String nom);
    Utilisateur findById(int id);
    Utilisateur findByEmailAndMdp(String email, String mdp);

}
