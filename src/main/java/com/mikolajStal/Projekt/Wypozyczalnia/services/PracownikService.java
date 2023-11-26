package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Stanowisko;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KontoRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.PracownikRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.StanowiskoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PracownikService {
    private final PracownikRepository pracownikRepository;
    private final StanowiskoRepository stanowiskoRepository;
    private final KontoRepository kontoRepository;

    public PracownikService(PracownikRepository pracownikRepository, StanowiskoRepository stanowiskoRepository, KontoRepository kontoRepository) {
        this.pracownikRepository = pracownikRepository;
        this.stanowiskoRepository = stanowiskoRepository;
        this.kontoRepository = kontoRepository;
    }

    public List<Pracownik> getPracownikow(){
        return pracownikRepository.findAll();
    }

    public void newPracownik(String imie, String nazwisko, String telefon, String email, String dataZa, String dataZW, String miejscowosc, String ulica, String nr_mieszkania, String nr_pocztowy, Long stan, Long konto) {

        LocalDate dataZatrudnienia = LocalDate.parse(dataZa);

        LocalDate dataZwolnienia;
        if (dataZW.isEmpty()) {
            dataZwolnienia = null;
        }
        else {
            dataZwolnienia = LocalDate.parse(dataZW);
        }

        Stanowisko stanowisko = stanowiskoRepository.findById(stan).get();
        Konto konto1 = kontoRepository.findById(konto).get();

        Pracownik pracownik = new Pracownik(
                imie,
                nazwisko,
                telefon,
                email,
                dataZatrudnienia,
                dataZwolnienia,
                miejscowosc,
                ulica,
                nr_mieszkania,
                nr_pocztowy,
                stanowisko,
                konto1);
        pracownikRepository.save(pracownik);
    }

    public void deletePracownik(Long id) {

        Pracownik pracownik = pracownikRepository.getById(id);
        pracownikRepository.delete(pracownik);
    }

    public Pracownik getPracownikById(Long id) {
        return pracownikRepository.getById(id);
    }

    public void savePracownik(Long id, String imie, String nazwisko, String telefon, String email, String dataZa, String dataZW, String miejscowosc, String ulica, String nr_mieszkania, String nr_pocztowy, Long stan, Long konto) {

        LocalDate dataZatrudnienia = LocalDate.parse(dataZa);

        LocalDate dataZwolnienia;
        if (dataZW.isEmpty()) {
            dataZwolnienia = null;
        }
        else {
            dataZwolnienia = LocalDate.parse(dataZW);
        }

        Stanowisko stanowisko = stanowiskoRepository.getById(stan);
        Konto konto1 = kontoRepository.getById(konto);

        Pracownik pracownik = new Pracownik(id,imie,nazwisko,telefon,email,dataZatrudnienia,dataZwolnienia,miejscowosc,ulica,nr_mieszkania,nr_pocztowy,stanowisko,konto1);
        pracownikRepository.save(pracownik);

    }
}
