package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Kategoria;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Klient;
import com.mikolajStal.Projekt.Wypozyczalnia.models.RodzajKlienta;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class KlientController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public KlientController(
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

    @RequestMapping(value = "/AwypiszK.html", method = RequestMethod.GET)
    public String wypiszKlientow(Model model)
    {
        List<Klient> klienci = klientService.getKlienci();
        List<RodzajKlienta> rodzajKlienta = rodzajKlientaService.getRodzajKlienta();
        model.addAttribute("klienci", klienci);
        model.addAttribute("rodzaj", rodzajKlienta);
        return("AwypiszK");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/AwypiszK.html", method = RequestMethod.POST)
    public String dodajKlienta(@RequestParam(value = "imie") String imie,
                               @RequestParam(value = "nazwisko") String nazwisko,
                               @RequestParam(value = "telefon") String telefon,
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "miejs") String miejscowosc,
                               @RequestParam(value = "ulica") String ulica,
                               @RequestParam(value = "nrm") String nr_mieszkania,
                               @RequestParam(value = "nrp") String nr_pocztowy,
                               @RequestParam(value = "typ") Long typ,
                               Model model)
    {
        klientService.newKlient(imie,nazwisko,telefon,email, miejscowosc,ulica,nr_mieszkania,nr_pocztowy, typ);
        return "redirect:/AwypiszK.html";
    }

    //=====================================================

    //Usuwanie

    @RequestMapping(value = "/klient/delete/{id}")
    public String usunKlienta(@PathVariable(value = "id") Long id)
    {

        klientService.deleteKlient(id);
        return "redirect:/AwypiszK.html";
    }



    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/klient/edit/{id}")
    public String edytujKlienta(@PathVariable(value = "id") Long id, Model model)
    {
        Klient klient = klientService.getKlientById(id);
        model.addAttribute("idKlienta", klient.getIdKlienta());
        model.addAttribute("imie", klient.getImie());
        model.addAttribute("nazwisko", klient.getNazwisko());
        model.addAttribute("email", klient.getEmail());
        model.addAttribute("telefon", klient.getTelefon());
        model.addAttribute("miejs", klient.getMiejscowosc());
        model.addAttribute("ulica", klient.getUlica());
        model.addAttribute("nrMieszkania", klient.getNrMieszkania());
        model.addAttribute("nrPocztowy", klient.getNrPocztowy());
        model.addAttribute("typ", klient.getTypKlienta());

        List<RodzajKlienta> rodzajKlienta = rodzajKlientaService.getRodzajKlienta();
        model.addAttribute("rodzaj", rodzajKlienta);

        return "editKlient";
    }


    @RequestMapping(value = "/klient/edit/zapisz", method = RequestMethod.POST)
    public String zapiszKlienta(@RequestParam(value = "id") Long id,
                                @RequestParam(value = "imie") String imie,
                                @RequestParam(value = "nazwisko") String nazwisko,
                                @RequestParam(value = "telefon") String telefon,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "miejs") String miejscowosc,
                                @RequestParam(value = "ulica") String ulica,
                                @RequestParam(value = "nrm") String nr_mieszkania,
                                @RequestParam(value = "nrp") String nr_pocztowy,
                                @RequestParam(value = "typ") Long typ,
                                  Model model)
    {
        klientService.saveKlient(id, imie, nazwisko, telefon, email, miejscowosc, ulica, nr_mieszkania, nr_pocztowy, typ);
        return "redirect:/AwypiszK.html";
    }




    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }

}
