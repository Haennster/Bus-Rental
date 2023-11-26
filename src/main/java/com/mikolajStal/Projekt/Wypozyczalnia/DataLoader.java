package com.mikolajStal.Projekt.Wypozyczalnia;

import com.mikolajStal.Projekt.Wypozyczalnia.models.*;
import com.mikolajStal.Projekt.Wypozyczalnia.repos.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataLoader {


    @Bean
    CommandLineRunner commandLineRunner(
                                        AutokarRepository autokarRepository,
                                        KategoriaRepository kategoriaRepository,
                                        KlientRepository klientRepository,
                                        KontoRepository kontoRepository,
                                        RodzajKlientaRepository rodzajKlientaRepository,
                                        StanowiskoRepository stanowiskoRepository,
                                        PracownikRepository pracownikRepository,
                                        WypozyczenieRepository wypozyczenieRepository) {
        return args -> {


            Kategoria kat1 = new Kategoria(
                    "Szkolny",
                    35,
                    30.5F
            );
            Kategoria kat2 = new Kategoria(
                    "Wycieczkowy",
                    50,
                    50.34F
            );
            Kategoria kat3 = new Kategoria(
                    "Daleko Dystansowy",
                    75,
                    60.75F
            );
            kategoriaRepository.saveAll(List.of(kat1, kat2, kat3));

            Autokar auto1 = new Autokar(
                    kat1,
                    "RJS 56sr",
                    "tak"
            );
            Autokar auto2 = new Autokar(
                    kat2,
                    "RJS r8nb",
                    "tak"
            );
            Autokar auto3 = new Autokar(
                    kat3,
                    "RJS 94f3",
                    "tak"
            );
            autokarRepository.saveAll(List.of(auto1, auto2, auto3));

            RodzajKlienta Rk1 = new RodzajKlienta(
                    "Osoba Prywatna"
            );
            RodzajKlienta Rk2 = new RodzajKlienta(
                    "Firma"
            );
            RodzajKlienta Rk3 = new RodzajKlienta(
                    "Szkoła"
            );
            rodzajKlientaRepository.saveAll(List.of(Rk1, Rk2, Rk3));

            Klient k1 = new Klient(
                    "Mikołaj",
                    "Stal",
                    "123456789",
                    "mik.stal@cos.pl",
                    "Jasło",
                    "Kościuszki",
                    "39a",
                    "38-200",
                    Rk1
            );
            Klient k2 = new Klient(
                    "Michał",
                    "Potyrała",
                    "836234872",
                    "potyrall@cos.pl",
                    "Jasło",
                    "Kościuszki",
                    "37a",
                    "38-200",
                    Rk2
            );
            klientRepository.saveAll(List.of(k1, k2));

            Konto kon1 = new Konto(
                    "Admin",
                    "Admin",
                    "administrator"
            );
            Konto kon2 = new Konto(
                    "janek",
                    "janeczek54",
                    "pracownik"
            );
            Konto kon3 = new Konto(
                    "Kuba_Kowalski",
                    "dfsg267dcv",
                    "pracownik"
            );
            Konto kon4 = new Konto(
                    "Mikolaj_Stal",
                    "8763kkkauwm",
                    "administrator"
            );
            kontoRepository.saveAll(List.of(kon1, kon2, kon3, kon4));

            Stanowisko st1 = new Stanowisko(
                    "Administrator"
            );
            Stanowisko st2 = new Stanowisko(
                    "Pracownik"
            );
            Stanowisko st3 = new Stanowisko(
                    "Kierowca"
            );
            stanowiskoRepository.saveAll(List.of(st1, st2, st3));

            Pracownik pr1 = new Pracownik(
                    "Mikołaj",
                    "Stal",
                    "532641295",
                    "stal.mikolaj@wp.pl",
                    LocalDate.of(2020, 6, 3),
                    null,
                    "Jasło",
                    "3-go Maja",
                    "13a",
                    "38-200",
                    st1,
                    kon4
            );
            Pracownik pr2 = new Pracownik(
                    "Kuba",
                    "Kowalski",
                    "839274837",
                    "kuba.kowal@wp.pl",
                    LocalDate.of(2015, 8, 26),
                    null,
                    "Krosno",
                    "Łukasiewicza",
                    "28",
                    "38-201",
                    st2,
                    kon3

            );
            pracownikRepository.saveAll(List.of(pr1, pr2));

            Wypozyczenie wy1 = new Wypozyczenie(
                    LocalDate.of(2021, 1, 2),
                    LocalDate.of(2021, 1, 7),
                    pr2,
                    1500,
                    1750,
                    800,
                    k2,
                    auto2
            );

            Wypozyczenie wy2 = new Wypozyczenie(
                    LocalDate.of(2021, 1, 20),
                    null,
                    pr2,
                    5000,
                    0,
                    1500,
                    k2,
                    auto3
            );
            wypozyczenieRepository.saveAll(List.of(wy1, wy2));


        };
    }
}
