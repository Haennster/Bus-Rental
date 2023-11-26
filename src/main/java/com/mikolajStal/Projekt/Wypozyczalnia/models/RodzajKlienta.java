package com.mikolajStal.Projekt.Wypozyczalnia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RodzajKlienta")
public class RodzajKlienta {

    @Id
    @SequenceGenerator(name = "RodzajKlienta",
            sequenceName = "RodzajKlienta_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "RodzajKlienta_sequence")
    private Long id;
    private String typ;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typKlienta")
    //@JoinColumn(name = "rodzajKlienta")
    private List<Klient> klienci = new ArrayList<>();

    public RodzajKlienta() {
    }

    public RodzajKlienta(String typ) {
        this.typ = typ;
    }

    public RodzajKlienta(Long id, String typ) {
        this.id = id;
        this.typ = typ;
    }

    @Override
    public String toString() {
        return "RodzajKlienta{" +
                "idTypuKlienta=" + id +
                ", typ='" + typ + '\'' +
                '}';
    }

    public Long getIdTypuKlienta() {
        return id;
    }

    public void setIdTypuKlienta(Long idTypuKlienta) {
        this.id = idTypuKlienta;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public List<Klient> getKlienci() {
        return klienci;
    }

    public void setKlienci(List<Klient> klienci) {
        this.klienci = klienci;
    }
}
