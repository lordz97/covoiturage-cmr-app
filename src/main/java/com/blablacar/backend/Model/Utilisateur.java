package com.blablacar.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "utilisateur")
@AllArgsConstructor @NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mdp;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String photo;
    private String adresse;
    private LocalDate dateInscription;
    private int mobileMoney;

    @OneToMany(mappedBy = "utilisateur",
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();



}
