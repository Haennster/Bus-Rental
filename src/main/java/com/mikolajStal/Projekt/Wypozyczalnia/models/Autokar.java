package com.mikolajStal.Projekt.Wypozyczalnia.models;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autokar")
public class Autokar {


    @Id
    @SequenceGenerator(name = "Autokar",
            sequenceName = "Autokar_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Autokar_sequence")
    private Long id;

    @ManyToOne
    @ToStringPlugin.Exclude
    private Kategoria idKategori;
    private String nrRejestracyjny;
    private String dostep;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autokar")
    private List<Wypozyczenie> wypozyczenie = new ArrayList<>();

    public Autokar() {
    }

    public Autokar(Kategoria idKategori, String nrRejestracyjny, String dostep) {
        this.idKategori = idKategori;
        this.nrRejestracyjny = nrRejestracyjny;
        this.dostep = dostep;
    }

    public Autokar(Long idAutokaru,
                   Kategoria idKategori,
                   String nrRejestracyjny,
                   String dostep) {
        this.id = idAutokaru;
        this.idKategori = idKategori;
        this.nrRejestracyjny = nrRejestracyjny;
        this.dostep = dostep;
    }

    public Autokar(int kat, String rej, String dost) {
    }

    @Override
    public String toString() {
        return "Autokar{" +
                "idAutokaru=" + id +
                ", idKategori=" + idKategori +
                ", nrRejestracyjny='" + nrRejestracyjny + '\'' +
                ", dostep='" + dostep + '\'' +
                '}';
    }

    public Long getIdAutokaru() {
        return id;
    }

    public void setIdAutokaru(Long idAutokaru) {
        this.id = idAutokaru;
    }

    public Kategoria getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Kategoria idKategori) {
        this.idKategori = idKategori;
    }

    public String getNrRejestracyjny() {
        return nrRejestracyjny;
    }

    public void setNrRejestracyjny(String nrRejestracyjny) {
        this.nrRejestracyjny = nrRejestracyjny;
    }

    public String getDostep() {
        return dostep;
    }

    public void setDostep(String dostep) {
        this.dostep = dostep;
    }

    public List<Wypozyczenie> getWypozyczenie() {
        return wypozyczenie;
    }

    public void setWypozyczenie(List<Wypozyczenie> wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }
}
