package com.mikolajStal.Projekt.Wypozyczalnia.repos;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KontoRepository extends JpaRepository<Konto, Long> {


    List<Konto> findByLogin(String login);
    List<Konto> findByLoginAndHaslo(String login, String haslo);
}
