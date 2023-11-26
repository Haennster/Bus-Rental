package com.mikolajStal.Projekt.Wypozyczalnia.repos;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Long> {
}
