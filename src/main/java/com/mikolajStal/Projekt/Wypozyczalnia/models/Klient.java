package com.mikolajStal.Projekt.Wypozyczalnia.models;

import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Klient")
public class Klient {


    @Id
    @SequenceGenerator(name = "Klient",
            sequenceName = "Klient_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Klient_sequence")
    private Long id;

    private String imie;
    private String nazwisko;
    private String telefon;
    private String email;
    private String miejscowosc;
    private String ulica;
    private String nrMieszkania;
    private String nrPocztowy;


    @ManyToOne
    @ToStringPlugin.Exclude
    private RodzajKlienta typKlienta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "klient")
    private List<Wypozyczenie> wypozyczenie = new ArrayList<>();

    public Klient() {
    }

    public Klient(String imie,
                  String nazwisko,
                  String telefon,
                  String email,
                  String miejscowosc,
                  String ulica,
                  String nrMieszkania,
                  String nrPocztowy,
                  RodzajKlienta typKlienta) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrMieszkania = nrMieszkania;
        this.nrPocztowy = nrPocztowy;
        this.typKlienta = typKlienta;
    }

    public Klient(Long id,
                  String imie,
                  String nazwisko,
                  String telefon,
                  String email,
                  String miejscowosc,
                  String ulica,
                  String nrMieszkania,
                  String nrPocztowy,
                  RodzajKlienta typKlienta) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrMieszkania = nrMieszkania;
        this.nrPocztowy = nrPocztowy;
        this.typKlienta = typKlienta;
    }


    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", ulica='" + ulica + '\'' +
                ", nrMieszkania='" + nrMieszkania + '\'' +
                ", nrPocztowy='" + nrPocztowy + '\'' +
                ", typKlienta=" + typKlienta +
                ", wypozyczenie=" + wypozyczenie +
                '}';
    }

    public Long getIdKlienta() {
        return id;
    }

    public void setIdKlienta(Long idKlienta) {
        this.id = idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RodzajKlienta getTypKlienta() {
        return typKlienta;
    }

    public void setTypKlienta(RodzajKlienta typKlienta) {
        this.typKlienta = typKlienta;
    }

    public List<Wypozyczenie> getWypozyczenie() {
        return wypozyczenie;
    }

    public void setWypozyczenie(List<Wypozyczenie> wypozyczenie) {
        this.wypozyczenie = wypozyczenie;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrMieszkania() {
        return nrMieszkania;
    }

    public void setNrMieszkania(String nrMieszkania) {
        this.nrMieszkania = nrMieszkania;
    }

    public String getNrPocztowy() {
        return nrPocztowy;
    }

    public void setNrPocztowy(String nrPocztowy) {
        this.nrPocztowy = nrPocztowy;
    }
}
