package com.mikolajStal.Projekt.Wypozyczalnia.repos;

import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodzajKlientaRepository extends JpaRepository<RodzajKlienta, Long> {
}
