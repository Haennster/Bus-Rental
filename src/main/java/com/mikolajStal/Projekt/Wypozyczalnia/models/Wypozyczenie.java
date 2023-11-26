package com.mikolajStal.Projekt.Wypozyczalnia.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.build.ToStringPlugin;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Wypozyczenie")
public class Wypozyczenie {


    @Id
    @SequenceGenerator(name = "Wypozyczenie",
            sequenceName = "Wypozyczenie_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Wypozyczenie_sequence")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataWypozyczenia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataZwrotu;

    @JsonIgnore
    @ManyToOne
    @ToStringPlugin.Exclude
    private Pracownik kierowca;
    private float cenaPrzewidywana;
    private float cenaOstateczna;
    private int odleglosc;

    @JsonIgnore
    @ManyToOne
    @ToStringPlugin.Exclude
    private Klient klient;

    @JsonIgnore
    @ManyToOne
    @ToStringPlugin.Exclude
    private Autokar autokar;

    public Wypozyczenie() {
    }

    public Wypozyczenie(LocalDate dataWypozyczenia,
                        LocalDate dataZwrotu,
                        Pracownik idKierowcy,
                        float cenaPrzewidywana,
                        float cenaOstateczna,
                        int odleglosc,
                        Klient klient,
                        Autokar autokar) {
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
        this.kierowca = idKierowcy;
        this.cenaPrzewidywana = cenaPrzewidywana;
        this.cenaOstateczna = cenaOstateczna;
        this.odleglosc = odleglosc;
        this.klient = klient;
        this.autokar = autokar;
    }

    public Wypozyczenie(Long idWypozyczenia,
                        LocalDate dataWypozyczenia,
                        LocalDate dataZwrotu,
                        Pracownik idKierowcy,
                        float cenaPrzewidywana,
                        float cenaOstateczna, int odleglosc, Klient klient, Autokar autokar) {
        this.id = idWypozyczenia;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
        this.kierowca = idKierowcy;
        this.cenaPrzewidywana = cenaPrzewidywana;
        this.cenaOstateczna = cenaOstateczna;
        this.odleglosc = odleglosc;
        this.klient = klient;
        this.autokar = autokar;
    }

    @Override
    public String toString() {
        return "Wypozyczenie{" +
                "id=" + id +
                ", dataWypozyczenia=" + dataWypozyczenia +
                ", dataZwrotu=" + dataZwrotu +
                ", idKierowcy=" + kierowca +
                ", cenaPrzewidywana=" + cenaPrzewidywana +
                ", cenaOstateczna=" + cenaOstateczna +
                ", odleglosc=" + odleglosc +
                ", klient=" + klient +
                ", autokar=" + autokar +
                '}';
    }

    public Long getIdWypozyczenia() {
        return id;
    }

    public void setIdWypozyczenia(Long idWypozyczenia) {
        this.id = idWypozyczenia;
    }

    public LocalDate getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(LocalDate dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    public LocalDate getDataZwrotu() {
        return dataZwrotu;
    }

    public void setDataZwrotu(LocalDate dataZwrotu) {
        this.dataZwrotu = dataZwrotu;
    }

    public Pracownik getIdKierowcy() {
        return kierowca;
    }

    public void setIdKierowcy(Pracownik idKierowcy) {
        this.kierowca = idKierowcy;
    }

    public float getCenaPrzewidywana() {
        return cenaPrzewidywana;
    }

    public void setCenaPrzewidywana(float cenaPrzewidywana) {
        this.cenaPrzewidywana = cenaPrzewidywana;
    }

    public float getCenaOstateczna() {
        return cenaOstateczna;
    }

    public void setCenaOstateczna(float cenaOstateczna) {
        this.cenaOstateczna = cenaOstateczna;
    }

    public int getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(int odleglosc) {
        this.odleglosc = odleglosc;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Autokar getAutokar() {
        return autokar;
    }

    public void setAutokar(Autokar autokar) {
        this.autokar = autokar;
    }
}
