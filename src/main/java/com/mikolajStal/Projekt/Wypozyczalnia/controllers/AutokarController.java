package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Autokar;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AutokarController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public AutokarController(
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

    @RequestMapping("/AwypiszA.html")
    public String wypiszAutokary(Model model)
    {
        List<Autokar> autokary = autokarService.getAutokary();
        List<Kategoria> kategorie = kategoriaService.getKategorie();
        model.addAttribute("autokary", autokary);
        model.addAttribute("kategorie", kategorie);
        return("AwypiszA");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/AwypiszA.html", method = RequestMethod.POST)
    public String dodajAutokar(@RequestParam(value = "kat") Long kat,
                               @RequestParam(value = "rej") String rej,
                               @RequestParam(value = "dost") String dost,
                               Model model)
    {
        autokarService.newAutokar(kat,rej,dost);
        return "redirect:/AwypiszA.html";
    }

    //=====================================================

    //Usuwanie

    @RequestMapping(value = "/autokar/delete/{id}")
    public String usunAutokar(@PathVariable(value = "id") Long id)
    {

        autokarService.deleteAutokar(id);
        return "redirect:/AwypiszA.html";
    }

    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/autokar/edit/{id}")
    public String edytujAutokar(@PathVariable(value = "id") Long id, Model model)
    {
        Autokar autokar = autokarService.getAutokarById(id);
        List<Kategoria> kategorie = kategoriaService.getKategorie();

        model.addAttribute("idAutokaru", autokar.getIdAutokaru());
        model.addAttribute("idKategorii", autokar.getIdKategori());
        model.addAttribute("nrRejestracyjny", autokar.getNrRejestracyjny());
        model.addAttribute("dostep", autokar.getDostep());
        model.addAttribute("kategorie", kategorie);

        return "editAutokar";
    }


    @RequestMapping(value = "/autokar/edit/zapisz", method = RequestMethod.POST)
    public String zapiszAutokar(@RequestParam(value = "idAutokaru") Long id,
                                @RequestParam(value = "kat") Long kat,
                               @RequestParam(value = "rej") String rej,
                               @RequestParam(value = "dost") String dost,
                               Model model)
    {

        autokarService.saveAutokar(id,kat,rej,dost);
        return "redirect:/AwypiszA.html";
    }



    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
