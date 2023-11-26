package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Klient;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KontoController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public KontoController(
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

    @RequestMapping("/Akonta.html")
    public String wypiszKonta(Model model)
    {
        List<Konto> konta = kontoService.getKonta();
        model.addAttribute("konta", konta);
        return("Akonta");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/Akonta.html", method = RequestMethod.POST)
    public String dodajKonto(@RequestParam(value = "login") String log,
                             @RequestParam(value = "haslo") String haslo,
                             @RequestParam(value = "dost") String dostep,
                             Model model)
    {
        kontoService.newKonto(log,haslo, dostep);
        return "redirect:/Akonta.html";
    }

    //=====================================================

    //Usuwanie

    @RequestMapping(value = "/konto/delete/{id}")
    public String usunKonto(@PathVariable(name = "id") Long id, Model model) throws Exception {
        try {
            kontoService.deleteKonto(id);
            return "redirect:/Akonta.html";
        }
        catch (Exception e)
        {
            throw new Exception("nie można usunąc tego konta ponieważ jest ono powiązane z pracownikiem");

        }
    }



    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/konto/edit/{id}")
    public String edytujKonto(@PathVariable(value = "id") Long id, Model model)
    {
        Konto konto = kontoService.getKontoById(id);
        model.addAttribute("idKonta", konto.getIdKonta());
        model.addAttribute("login", konto.getLogin());
        model.addAttribute("haslo", konto.getHaslo());
        model.addAttribute("dostep", konto.getDostep());

        return "editKonto";
    }


    @RequestMapping(value = "/konto/edit/zapisz", method = RequestMethod.POST)
    public String zapiszKonto(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "login") String login,
                                @RequestParam(value = "haslo") String haslo,
                                @RequestParam(value = "dostep") String dostep,
                                Model model)
    {
        kontoService.saveKonto(id, login, haslo, dostep);
        return "redirect:/Akonta.html";
    }



    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
