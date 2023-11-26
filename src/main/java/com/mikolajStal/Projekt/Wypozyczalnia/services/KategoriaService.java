package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriaService {

    private final KategoriaRepository kategoriaRepository;

    public KategoriaService(KategoriaRepository kategoriaRepository) {
        this.kategoriaRepository = kategoriaRepository;
    }

    public List<Kategoria> getKategorie(){
        return kategoriaRepository.findAll();
    }

    public void addNewKategoria(String kategoria, int pojemnosc, float spalanie)
    {

        Kategoria kat = new Kategoria(kategoria, pojemnosc, spalanie);
        kategoriaRepository.save(kat);
    }

    public void deleteKategoria(Long idKat) {

        Kategoria kategoria = kategoriaRepository.findById(idKat).get();

        kategoriaRepository.delete(kategoria);
    }

    public Kategoria getKategorieById(Long id) {

        return kategoriaRepository.getById(id);
    }

    public void saveKategorie(Long id, String kat, int poj, float spal) {
        Kategoria kategoria = new Kategoria(id,kat, poj, spal);
        kategoriaRepository.save(kategoria);
    }
}
