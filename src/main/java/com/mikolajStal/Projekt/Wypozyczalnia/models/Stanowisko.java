package com.mikolajStal.Projekt.Wypozyczalnia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Stanowisko")
public class Stanowisko {


    @Id
    @SequenceGenerator(name = "Stanowisko",
            sequenceName = "Stanowisko_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Stanowisko_sequence")
    private Long id;
    private String stanowisko;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stanowiska")
    private List<Pracownik> pracownik = new ArrayList<>();

    public Stanowisko() {
    }

    public Stanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public Stanowisko(Long idStanowiska, String stanowisko) {
        this.id = idStanowiska;
        this.stanowisko = stanowisko;
    }

    @Override
    public String toString() {
        return "Stanowisko{" +
                "idStanowiska=" + id +
                ", stanowisko='" + stanowisko + '\'' +
                '}';
    }

    public Long getIdStanowiska() {
        return id;
    }

    public void setIdStanowiska(Long idStanowiska) {
        this.id = idStanowiska;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public List<Pracownik> getPracownik() {
        return pracownik;
    }

    public void setPracownik(List<Pracownik> pracownik) {
        this.pracownik = pracownik;
    }
}
