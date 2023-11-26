package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Autokar;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KategoriaController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public KategoriaController(
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

    @RequestMapping("/Akategorie.html")
    public String wypiszKategorie(Model model)
    {

        List<Kategoria> kategorie = kategoriaService.getKategorie();
        model.addAttribute("kategorie", kategorie);
        return("Akategorie");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/Akategorie.html", method = RequestMethod.POST)
    public String dodajKategorie(@RequestParam(value = "nazwa") String nazwa,
                                 @RequestParam(value = "pojem") int pojem,
                                 @RequestParam(value = "spal") float spal,
                                 Model model)
    {
        kategoriaService.addNewKategoria(nazwa,pojem, spal);
        return "redirect:/Akategorie.html";
    }

    //=====================================================

    //Usuwanie

    @RequestMapping(value = "/kategoria/delete/{id}")
    public String usunKategorie(@PathVariable(name = "id") Long idKat,
                                Model model)
    {

        kategoriaService.deleteKategoria(idKat);

        return "redirect:/Akategorie.html";
    }

    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/kategoria/edit/{id}")
    public String edytujKategorie(@PathVariable(value = "id") Long id, Model model)
    {
        Kategoria kategoria = kategoriaService.getKategorieById(id);
        model.addAttribute("idKategori", kategoria.getIdKategori());
        model.addAttribute("kategoria", kategoria.getKategoria());
        model.addAttribute("pojemnosc", kategoria.getPojemnosc());
        model.addAttribute("spalanie", kategoria.getSpalanie());

        return "editKategoria";
    }


    @RequestMapping(value = "/kategoria/edit/zapisz", method = RequestMethod.POST)
    public String zapiszKategorie(@RequestParam(value = "idKategori") Long id,
                                @RequestParam(value = "kat") String kat,
                                @RequestParam(value = "poj") int poj,
                                @RequestParam(value = "spal") float spal,
                                Model model)
    {
        kategoriaService.saveKategorie(id,kat,poj,spal);
        return "redirect:/Akategorie.html";
    }



    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }

}
