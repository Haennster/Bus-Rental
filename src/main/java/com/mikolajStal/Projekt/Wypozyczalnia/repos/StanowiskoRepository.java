package com.mikolajStal.Projekt.Wypozyczalnia.repos;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Stanowisko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StanowiskoRepository extends JpaRepository<Stanowisko, Long> {
}
