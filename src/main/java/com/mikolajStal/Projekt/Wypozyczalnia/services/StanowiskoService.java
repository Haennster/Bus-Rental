package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Stanowisko;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.StanowiskoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StanowiskoService {
    private final StanowiskoRepository stanowiskoRepository;

    public StanowiskoService(StanowiskoRepository stanowiskoRepository) {
        this.stanowiskoRepository = stanowiskoRepository;
    }

    public List<Stanowisko> getStanowisko(){
        return stanowiskoRepository.findAll();
    }

    public void newStan(String nazwa) {

        Stanowisko stanowisko = new Stanowisko(nazwa);
        stanowiskoRepository.save(stanowisko);
    }

    public Stanowisko getStanowiskoById(Long id) {
        return stanowiskoRepository.getById(id);
    }

    public void saveStanowisko(Long id, String stanowisko) {

        Stanowisko stanowisko1 = new Stanowisko(id, stanowisko);
        stanowiskoRepository.save(stanowisko1);
    }
}
