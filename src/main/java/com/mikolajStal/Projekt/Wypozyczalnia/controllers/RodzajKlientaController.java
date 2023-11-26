package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RodzajKlientaController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public RodzajKlientaController(
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

    @RequestMapping(value = "/AwypiszRK.html", method = RequestMethod.GET)
    public String wypiszTypyKlientow(Model model)
    {
        List<RodzajKlienta> rodzajKlienta = rodzajKlientaService.getRodzajKlienta();
        model.addAttribute("Rodzaj", rodzajKlienta);
        return("AwypiszRK");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/AwypiszRK.html", method = RequestMethod.POST)
    public String dodajPracownika(@RequestParam(value = "typKlienta") String typ,
                                  Model model)
    {
        rodzajKlientaService.nowTyp(typ);
        return "redirect:/AwypiszRK.html";
    }


    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/rodzaj/edit/{id}")
    public String edytujTyp(@PathVariable(value = "id") Long id, Model model)
    {
        RodzajKlienta rodzajKlienta = rodzajKlientaService.getKategorieById(id);
        model.addAttribute("idRodzaj", rodzajKlienta.getIdTypuKlienta());
        model.addAttribute("typ", rodzajKlienta.getTyp());


        return "editRodzajKlienta";
    }


    @RequestMapping(value = "/rodzaj/edit/zapisz", method = RequestMethod.POST)
    public String zapiszTyp(@RequestParam(value = "idRodzaju") Long id,
                                  @RequestParam(value = "rodzaj") String rodzaj,
                                  Model model)
    {
        rodzajKlientaService.saveRodzajKlienta(id,rodzaj);
        return "redirect:/AwypiszRK.html";
    }


    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
