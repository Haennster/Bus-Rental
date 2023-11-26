package com.mikolajStal.Projekt.Wypozyczalnia.services;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Autokar;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Klient;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Wypozyczenie;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.AutokarRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.KlientRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.PracownikRepository;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.WypozyczenieRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WypozyczenieService {
    private final WypozyczenieRepository wypozyczenieRepository;
    private final PracownikRepository pracownikRepository;
    private final KlientRepository klientRepository;
    private final AutokarRepository autokarRepository;

    public WypozyczenieService(WypozyczenieRepository wypozyczenieRepository, PracownikRepository pracownikRepository, KlientRepository klientRepository, AutokarRepository autokarRepository) {
        this.wypozyczenieRepository = wypozyczenieRepository;
        this.pracownikRepository = pracownikRepository;
        this.klientRepository = klientRepository;
        this.autokarRepository = autokarRepository;
    }

    public List<Wypozyczenie> getWypozyczenia(){
        return wypozyczenieRepository.findAll();
    }

    public void wypozyczenie(String dat1, String dat2, float cen1, float cen2, int odl,Long kierowca,Long klient,Long autokar) {

        LocalDate dataWypozyczenia = LocalDate.parse(dat1);

        LocalDate dataZwrotu;
        if (dat2.isEmpty()) {
            dataZwrotu = null;
        }
        else {
            dataZwrotu = LocalDate.parse(dat2);
        }

        Pracownik pracownik = pracownikRepository.getById(kierowca);
        Klient klient1 = klientRepository.getById(klient);
        Autokar autokar1 = autokarRepository.getById(autokar);

        Wypozyczenie wypozyczenie = new Wypozyczenie(dataWypozyczenia,dataZwrotu, pracownik, cen1, cen2,odl,klient1,autokar1);

        wypozyczenieRepository.save(wypozyczenie);
    }

    public void deleteWypozyczenie(Long id) {

        Wypozyczenie wypozyczenie = wypozyczenieRepository.getById(id);
        wypozyczenieRepository.delete(wypozyczenie);
    }

    public Wypozyczenie getWypozyczenieByid(Long id) {

        return wypozyczenieRepository.getById(id);
    }

    public void saveWypozyczenie(Long id, String dataWyp, String dataZwr, Long kierowca, float cenaPrzewi, float cenaOstat, int odleglosc, Long klient, Long autokar) {

        LocalDate dataWypozyczenia = LocalDate.parse(dataWyp);

        LocalDate dataZwrotu;
        if (dataZwr.isEmpty()) {
            dataZwrotu = null;
        }
        else {
            dataZwrotu = LocalDate.parse(dataZwr);
        }

        Pracownik pracownik = pracownikRepository.getById(kierowca);
        Klient klient1 = klientRepository.getById(klient);
        Autokar autokar1 = autokarRepository.getById(autokar);

        Wypozyczenie wypozyczenie = new Wypozyczenie(id, dataWypozyczenia,dataZwrotu, pracownik, cenaPrzewi, cenaOstat,odleglosc,klient1,autokar1);

        wypozyczenieRepository.save(wypozyczenie);
    }
}
