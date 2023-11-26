package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Autokar;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.AutokarRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutokarService {

    private final AutokarRepository autokarRepository;
    private final KategoriaRepository kategoriaRepository;

    @Autowired
    public AutokarService(AutokarRepository autokarRepository, KategoriaRepository kategoriaRepository) {
        this.autokarRepository = autokarRepository;
        this.kategoriaRepository = kategoriaRepository;
    }

    public List<Autokar> getAutokary(){
        return autokarRepository.findAll();
    }

    public void newAutokar(Long kat, String rej, String dost) {

        Kategoria kategoria = kategoriaRepository.findById(kat).get();
        Autokar autokar = new Autokar(kategoria, rej, dost);

        autokarRepository.save(autokar);

    }

    public void deleteAutokar(Long id) {

        Autokar autokar = autokarRepository.getById(id);

        autokarRepository.delete(autokar);
    }

    public Autokar getAutokarById(Long id) {

        return autokarRepository.getById(id);
    }

    public void saveAutokar(Long id, Long idKat, String nrRej, String dostep)
    {
        Kategoria kategoria = kategoriaRepository.getById(idKat);
        Autokar autokar = new Autokar(id, kategoria, nrRej, dostep);
        autokarRepository.save(autokar);
    }

}
