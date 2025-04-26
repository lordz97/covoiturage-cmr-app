package com.blablacar.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    @Column(nullable = false)
    private String nom;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false)
    private String mdp;

    @Getter
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Getter
    @Setter
    private String photo;
    @Getter
    @Setter
    private String adresse;
    @Getter
    @Setter
    private LocalDate dateInscription;
    @Getter
    @Setter
    private int mobileMoney;

    // Constructeur par défaut
    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String email, String mdp, Role role,
                         String photo, String adresse, LocalDate dateInscription, int mobileMoney) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.photo = photo;
        this.adresse = adresse;
        this.dateInscription = dateInscription;
        this.mobileMoney = mobileMoney;
    }

}
