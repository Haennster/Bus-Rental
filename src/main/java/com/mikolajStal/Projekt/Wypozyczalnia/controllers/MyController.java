package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.*;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MyController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public MyController(
                        AutokarService autokarService,
                        KategoriaService kategoriaService,
                        KlientService klientService,
                        KontoService kontoService,
                        PracownikService pracownikService,
                        RodzajKlientaService rodzajKlientaService,
                        StanowiskoService stanowiskoService,
                        WypozyczenieService wypozyczenieService) {
        this.autokarService = autokarService;
        this.kategoriaService = kategoriaService;
        this.klientService = klientService;
        this.kontoService = kontoService;
        this.pracownikService = pracownikService;
        this.rodzajKlientaService = rodzajKlientaService;
        this.stanowiskoService = stanowiskoService;
        this.wypozyczenieService = wypozyczenieService;


    }

    @RequestMapping("/wyloguj")
    public String wyloguj(Model model)
    {
        return "index";
    }

    @RequestMapping(value = "/zaloguj", method = RequestMethod.POST)
    public String logowanie(@RequestParam(value = "login") String login,
                            @RequestParam(value = "haslo") String haslo,
                            Model model)
    {

        if(kontoService.logowanie(login, haslo)){return("adminMain");}

        return "index";
    }

    @RequestMapping("/adminMain.html")
    public String home(Model model)
    {

        return "adminMain";
    }






    //Obsluga bledow

    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
