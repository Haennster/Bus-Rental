package com.mikolajStal.Projekt.Wypozyczalnia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.build.ToStringPlugin;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Pracownik")
public class Pracownik {

    @Id
    @SequenceGenerator(name = "Pracownik",
            sequenceName = "Pracownik_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "Pracownik_sequence")
    private Long id;

    private String imie;
    private String nazwisko;
    private String telefon;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataZatrudnienia;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataZwolnienia;
    private String miejscowosc;
    private String ulica;
    private String nrMieszkania;
    private String nrPocztowy;

    @ManyToOne
    @ToStringPlugin.Exclude
    private Stanowisko stanowiska;

    @OneToOne
    @JoinColumn(name = "konto_id", referencedColumnName = "id")
    private Konto konto;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "kierowca")
    private List<Wypozyczenie> wypozyczenie = new ArrayList<>();

    public Pracownik() {
    }

    public Pracownik(String imie,
                     String nazwisko,
                     String telefon,
                     String email,
                     LocalDate dataZatrudnienia,
                     LocalDate dataZwolnienia,
                     String miejscowosc,
                     String ulica,
                     String nrMieszkania,
                     String nrPocztowy,
                     Stanowisko idStanowsiska,
                     Konto idKonta
    ) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
        this.dataZatrudnienia = dataZatrudnienia;
        this.dataZwolnienia = dataZwolnienia;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrMieszkania = nrMieszkania;
        this.nrPocztowy = nrPocztowy;
        this.stanowiska = idStanowsiska;
        this.konto = idKonta;
    }

    public Pracownik(Long id,
                     String imie,
                     String nazwisko,
                     String telefon,
                     String email,
                     LocalDate dataZatrudnienia,
                     LocalDate dataZwolnienia,
                     String miejscowosc,
                     String ulica,
                     String nrMieszkania,
                     String nrPocztowy,
                     Stanowisko stanowiska,
                     Konto konto) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.telefon = telefon;
        this.email = email;
        this.dataZatrudnienia = dataZatrudnienia;
        this.dataZwolnienia = dataZwolnienia;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrMieszkania = nrMieszkania;
        this.nrPocztowy = nrPocztowy;
        this.stanowiska = stanowiska;
        this.konto = konto;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "idPracownika=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                ", dataZatrudnienia=" + dataZatrudnienia +
                ", dataZwolnienia=" + dataZwolnienia +
                ", idStanowsiska=" + stanowiska +
                ", idKonta=" + konto +
                '}';
    }

    public Long getIdPracownika() {
        return id;
    }

    public void setIdPracownika(Long idPracownika) {
        this.id = idPracownika;
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

    public LocalDate getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(LocalDate dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public LocalDate getDataZwolnienia() {
        return dataZwolnienia;
    }

    public void setDataZwolnienia(LocalDate dataZwolnienia) {
        this.dataZwolnienia = dataZwolnienia;
    }

    public Stanowisko getIdStanowsiska() {
        return stanowiska;
    }

    public void setIdStanowsiska(Stanowisko idStanowsiska) {
        this.stanowiska = idStanowsiska;
    }

    public Konto getIdKonta() {
        return konto;
    }

    public void setIdKonta(Konto idKonta) {
        this.konto = idKonta;
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
