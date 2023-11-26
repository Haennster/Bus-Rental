package com.mikolajStal.Projekt.Wypozyczalnia.repos;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownikRepository extends JpaRepository<Pracownik, Long> {

    List<Pracownik> findByKonto(Konto konto);
}
