package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Klient;
import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KlientRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.RodzajKlientaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KlientService {

    private final KlientRepository klientRepository;
    private final RodzajKlientaRepository rodzajKlientaRepository;

    public KlientService(KlientRepository klientRepository, RodzajKlientaRepository rodzajKlientaRepository) {
        this.klientRepository = klientRepository;
        this.rodzajKlientaRepository = rodzajKlientaRepository;
    }

    public List<Klient> getKlienci(){
        return klientRepository.findAll();
    };

    public void newKlient(String imie, String nazwisko, String telefon, String email, String miejscowosc, String ulica, String nrMieszkania, String nrPocztowy, Long typ) {

        RodzajKlienta rodzajKlienta = rodzajKlientaRepository.findById(typ).get();

        Klient klient = new Klient(imie,nazwisko,telefon,email, miejscowosc , ulica, nrMieszkania, nrPocztowy, rodzajKlienta);
        klientRepository.save(klient);
    }

    public void deleteKlient(Long id) {

        Klient klient = klientRepository.getById(id);

        klientRepository.delete(klient);
    }

    public Klient getKlientById(Long id) {

        return klientRepository.getById(id);
    }

    public void saveKlient(Long id, String imie, String nazwisko, String telefon, String email, String miejscowosc, String ulica, String nr_mieszkania, String nr_pocztowy, Long typ) {

        RodzajKlienta rodzajKlienta = rodzajKlientaRepository.getById(typ);
        Klient klient = new Klient(id,imie,nazwisko,telefon,email,miejscowosc,ulica,nr_mieszkania,nr_pocztowy,rodzajKlienta);
        klientRepository.save(klient);

    }
}
