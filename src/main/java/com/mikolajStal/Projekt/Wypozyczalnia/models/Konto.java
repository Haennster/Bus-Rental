package com.mikolajStal.Projekt.Wypozyczalnia.models;

import javax.persistence.*;

@Entity
@Table(name = "Konto")
public class Konto {

    @Id
    @SequenceGenerator(name = "Konto",
            sequenceName = "Konto_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Konto_sequence")
    private Long id;

    private String login;
    private String haslo;
    private String dostep;

    public Konto() {
    }

    public Konto(String login, String haslo, String dostep) {
        this.login = login;
        this.haslo = haslo;
        this.dostep = dostep;
    }

    public Konto(Long idKonta,
                 String login,
                 String haslo,
                 String dostep) {
        this.id = idKonta;
        this.login = login;
        this.haslo = haslo;
        this.dostep = dostep;
    }

    @Override
    public String toString() {
        return "Konto{" +
                "idKonta=" + id +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", dostep='" + dostep + '\'' +
                '}';
    }

    public Long getIdKonta() {
        return id;
    }

    public void setIdKonta(Long idKonta) {
        this.id = idKonta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getDostep() {
        return dostep;
    }

    public void setDostep(String dostep) {
        this.dostep = dostep;
    }
}
