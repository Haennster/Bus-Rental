package com.mikolajStal.Projekt.Wypozyczalnia.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Kategoria")
public class Kategoria {

    @Id
    @SequenceGenerator(name = "Kategoria",
            sequenceName = "Kategoria_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Kategoria_sequence")
    private Long id;

    private String kategoria;
    private int pojemnosc;
    private float spalanie;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategori")
    private List<Autokar> autokar = new ArrayList<>();

    public Kategoria() {
    }

    public Kategoria(String kategoria, int pojemnosc, float spalanie) {
        this.kategoria = kategoria;
        this.pojemnosc = pojemnosc;
        this.spalanie = spalanie;
    }

    public Kategoria(Long idKategori,
                     String kategoria,
                     int pojemnosc,
                     float spalanie) {
        this.id = idKategori;
        this.kategoria = kategoria;
        this.pojemnosc = pojemnosc;
        this.spalanie = spalanie;
    }

    @Override
    public String toString() {
        return "Kategoria{" +
                "idKategori=" + id +
                ", kategoria='" + kategoria + '\'' +
                ", pojemnosc=" + pojemnosc +
                ", spalanie=" + spalanie +
                '}';
    }

    public Long getIdKategori() {
        return id;
    }

    public void setIdKategori(Long idKategori) {
        this.id = idKategori;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public float getSpalanie() {
        return spalanie;
    }

    public void setSpalanie(float spalanie) {
        this.spalanie = spalanie;
    }

    public List<Autokar> getAutokar() {
        return autokar;
    }

    public void setAutokar(List<Autokar> autokar) {
        this.autokar = autokar;
    }
}
