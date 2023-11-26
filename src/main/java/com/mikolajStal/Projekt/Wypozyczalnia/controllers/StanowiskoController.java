package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Stanowisko;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StanowiskoController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public StanowiskoController(
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
    //Wypisywanie

    @RequestMapping("/Astanowiska.html")
    public String wypiszStanowiska(Model model)
    {
        List<Stanowisko> stanowiska = stanowiskoService.getStanowisko();
        model.addAttribute("stanowiska", stanowiska);
        return("Astanowiska");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/Astanowiska.html", method = RequestMethod.POST)
    public String dodajStan(@RequestParam(value = "nazwa") String nazwa, Model model)
    {
        stanowiskoService.newStan(nazwa);
        return "redirect:/Astanowiska.html";
    }


    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/stanowisko/edit/{id}")
    public String edytujStanowisko(@PathVariable(value = "id") Long id, Model model)
    {
        Stanowisko stanowisko = stanowiskoService.getStanowiskoById(id);
        model.addAttribute("idStanowiska", stanowisko.getIdStanowiska());
        model.addAttribute("stanowisko", stanowisko.getStanowisko());


        return "editStanowisko";
    }


    @RequestMapping(value = "/stanowisko/edit/zapisz", method = RequestMethod.POST)
    public String zapiszStanowisko(@RequestParam(value = "idStanowiska") Long id,
                                  @RequestParam(value = "stanowisko") String stanowisko,
                                  Model model)
    {
        stanowiskoService.saveStanowisko(id,stanowisko);
        return "redirect:/Astanowiska.html";
    }



    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
