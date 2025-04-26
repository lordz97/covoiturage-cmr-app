package com.blablacar.backend.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class ConnexionRequest {
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String mdp;

    public ConnexionRequest() {}

    public ConnexionRequest(String email, String mdp, LocalDate dateInscription) {
        this.email = email;
        this.mdp = mdp;
    }
}
