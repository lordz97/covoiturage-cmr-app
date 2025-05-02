package com.blablacar.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "trip")
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String villeDepart;

    @Column(nullable = false)
    private String villeArrivee;

    private int nbPlaces;

    private int prix;

    @OneToMany(mappedBy = "trip",
    cascade = CascadeType.ALL, orphanRemoval = true)//permet de manipuler des données qui n'ont pas encore été cree en
    //bd et de les sauvegarder par la suite, l'autre supprimera une reservation qui a ete enlevee de la liste
    // reservations de cette classe dans la bd
    private List<Reservation> reservations = new ArrayList<>();

}
