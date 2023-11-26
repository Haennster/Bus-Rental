package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.RodzajKlientaRepository;
import org.springframework.stereotype.Service;

import javax.sql.RowSetMetaData;
import java.util.List;

@Service
public class RodzajKlientaService {

    private final RodzajKlientaRepository rodzajKlientaRepository;

    public RodzajKlientaService(RodzajKlientaRepository rodzajKlientaRepository) {
        this.rodzajKlientaRepository = rodzajKlientaRepository;
    }

    public List<RodzajKlienta> getRodzajKlienta(){
        return rodzajKlientaRepository.findAll();
    }

    public void nowTyp(String typ) {


        RodzajKlienta rodzajKlienta = new RodzajKlienta(typ);
        rodzajKlientaRepository.save(rodzajKlienta);
    }

    public RodzajKlienta getKategorieById(Long id) {
        return rodzajKlientaRepository.getById(id);
    }

    public void saveRodzajKlienta(Long id, String rodzaj) {

        RodzajKlienta rodzajKlienta = new RodzajKlienta(id, rodzaj);
        rodzajKlientaRepository.save(rodzajKlienta);
    }
}
