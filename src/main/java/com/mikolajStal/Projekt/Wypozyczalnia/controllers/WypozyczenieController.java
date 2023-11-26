package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Autokar;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Klient;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Wypozyczenie;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class WypozyczenieController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public WypozyczenieController(
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

    @RequestMapping("/ApokazW.html")
    public String wypiszWypozyczenia(Model model)
    {
        List<Wypozyczenie> wypozyczenia = wypozyczenieService.getWypozyczenia();
        model.addAttribute("wypozyczenia", wypozyczenia);

        List<Pracownik> pracownicy = pracownikService.getPracownikow();
        model.addAttribute("pracownicy", pracownicy);

        List<Klient> klienci = klientService.getKlienci();
        model.addAttribute("klienci", klienci);

        List<Autokar> autokary = autokarService.getAutokary();
        model.addAttribute("autokary", autokary);
        return("ApokazW");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/ApokazW.html", method = RequestMethod.POST)
    public String dodajWypozyczenie(@RequestParam(value = "dat1") String dat1,
                               @RequestParam(value = "dat2") String dat2,
                               @RequestParam(value = "cen1") float cen1,
                               @RequestParam(value = "cen2") float cen2,
                               @RequestParam(value = "odl") int odl,
                               @RequestParam(value = "prac") Long kierowca,
                               @RequestParam(value = "klie") Long klient,
                               @RequestParam(value = "autok") Long autokar,
                               Model model)
    {
        wypozyczenieService.wypozyczenie(dat1,dat2,cen1,cen2,odl, kierowca, klient, autokar);
        return "redirect:/ApokazW.html";
    }



    //Usuwanie

    @RequestMapping(value = "/wypozyczenie/delete/{id}")
    public String usunWypozyczenie(@PathVariable(value = "id") Long id)
    {

        wypozyczenieService.deleteWypozyczenie(id);
        return "redirect:/ApokazW.html";
    }


    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/wypozyczenie/edit/{id}")
    public String edytujWypozyczenie(@PathVariable(value = "id") Long id, Model model)
    {
        Wypozyczenie wypozyczenie = wypozyczenieService.getWypozyczenieByid(id);
        model.addAttribute("idWypozyczenia", wypozyczenie.getIdWypozyczenia());
        model.addAttribute("dataWypozyczenia", wypozyczenie.getDataWypozyczenia());
        model.addAttribute("dataZwrotu", wypozyczenie.getDataZwrotu());
        model.addAttribute("kierowca", wypozyczenie.getIdKierowcy());
        model.addAttribute("cenaPrzewidywana", wypozyczenie.getCenaPrzewidywana());
        model.addAttribute("cenaOstateczna", wypozyczenie.getCenaOstateczna());
        model.addAttribute("odleglosc", wypozyczenie.getOdleglosc());
        model.addAttribute("klient", wypozyczenie.getKlient());
        model.addAttribute("autokar", wypozyczenie.getAutokar());

        List<Pracownik> pracownicy = pracownikService.getPracownikow();
        model.addAttribute("pracownicy", pracownicy);

        List<Klient> klienci = klientService.getKlienci();
        model.addAttribute("klienci", klienci);

        List<Autokar> autokary = autokarService.getAutokary();
        model.addAttribute("autokary", autokary);


        return "editWypozyczenie";
    }


    @RequestMapping(value = "/wypozyczenie/edit/save", method = RequestMethod.POST)
    public String zapiszWypozyczenie(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "dataWyp") String dataWyp,
                                  @RequestParam(value = "dataZwr") String dataZwr,
                                  @RequestParam(value = "kierowca") Long kierowca,
                                  @RequestParam(value = "cenaPrzewi") float cenaPrzewi,
                                  @RequestParam(value = "cenaOstat") float cenaOstat,
                                  @RequestParam(value = "odleglosc") int odleglosc,
                                  @RequestParam(value = "klient") Long klient,
                                  @RequestParam(value = "autokar") Long autokar,
                                  Model model)
    {
        wypozyczenieService.saveWypozyczenie(id, dataWyp, dataZwr, kierowca,cenaPrzewi,cenaOstat,odleglosc,klient,autokar);
        return "redirect:/ApokazW.html";
    }


    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }
}
