package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KontoRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.PracownikRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KontoService {

    private final KontoRepository kontoRepository;
    private final PracownikRepository pracownikRepository;

    public KontoService(KontoRepository kontoRepository, PracownikRepository pracownikRepository) {
        this.kontoRepository = kontoRepository;
        this.pracownikRepository = pracownikRepository;
    }

    public List<Konto> getKonta(){

        return kontoRepository.findAll();
    }


    public boolean logowanie(String login, String haslo)
    {
        Konto konto = kontoRepository.findAll().stream().
                filter(konto1 -> login.equals(konto1.getLogin()))
                .findAny()
                .orElse(null);


        if( konto == null) { return false;}

        return konto.getLogin().equals(login) && konto.getHaslo().equals(haslo);

    }

    public void newKonto(String log, String haslo, String dostep) {

        Konto konto = new Konto(log, haslo, dostep);

        kontoRepository.save(konto);
    }

    public void deleteKonto(Long id) {

            Konto konto = kontoRepository.findById(id).get();
            List<Pracownik> pracownik = pracownikRepository.findAll();

            kontoRepository.delete(konto);
    }

    public Konto getKontoById(Long id) {
        return kontoRepository.getById(id);
    }

    public void saveKonto(Long id, String login, String haslo, String dostep) {

        Konto konto = new Konto(id,login,haslo,dostep);
        kontoRepository.save(konto);
    }
}
