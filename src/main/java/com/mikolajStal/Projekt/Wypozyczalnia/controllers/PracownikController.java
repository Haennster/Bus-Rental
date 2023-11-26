package com.mikolajStal.Projekt.Wypozyczalnia.controllers;

import com.mikolajStal.Projekt.Wypozyczalnia.models.Konto;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Pracownik;
import com.mikolajStal.Projekt.Wypozyczalnia.models.Stanowisko;
import com.mikolajStal.Projekt.Wypozyczalnia.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PracownikController {

    private final AutokarService autokarService;
    private final KategoriaService kategoriaService;
    private final KlientService klientService;
    private final KontoService kontoService;
    private final PracownikService pracownikService;
    private final RodzajKlientaService rodzajKlientaService;
    private final StanowiskoService stanowiskoService;
    private final WypozyczenieService wypozyczenieService;


    @Autowired
    public PracownikController(
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

    @RequestMapping(value = "/AwypiszP.html", method = RequestMethod.GET)
    public String wypiszPracownikow(Model model)
    {
        List<Pracownik> pracownicy = pracownikService.getPracownikow();
        model.addAttribute("pracownicy", pracownicy);

        List<Stanowisko> stanowiska = stanowiskoService.getStanowisko();
        model.addAttribute("stanowiska", stanowiska);

        List<Konto> konta = kontoService.getKonta();
        model.addAttribute("konta", konta);

        return("AwypiszP");
    }

    //=====================================================

    //Dodawanie

    @RequestMapping(value = "/AwypiszP.html", method = RequestMethod.POST)
    public String dodajPracownika(@RequestParam(value = "imie") String imie,
                                  @RequestParam(value = "nazwisko") String nazwisko,
                                  @RequestParam(value = "telefon") String telefon,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "dataZa") String dataZa,
                                  @RequestParam(value = "dataZw") String dataZW,
                                  @RequestParam(value = "miejs") String miejscowosc,
                                  @RequestParam(value = "ulica") String ulica,
                                  @RequestParam(value = "nrm") String nr_mieszkania,
                                  @RequestParam(value = "nrp") String nr_pocztowy,
                                  @RequestParam(value = "stan") Long stan,
                                  @RequestParam(value = "konto") Long konto,
                                  Model model)
    {
        pracownikService.newPracownik(imie,nazwisko,telefon,email,dataZa,dataZW, miejscowosc,ulica,nr_mieszkania,nr_pocztowy, stan, konto);
        return "redirect:/AwypiszP.html";
    }

    @RequestMapping(value = "/pracownik/delete/{id}")
    public String usunPracownika(@PathVariable(value = "id") Long id)
    {

        pracownikService.deletePracownik(id);
        return "redirect:/AwypiszP.html";
    }

    //=====================================================

    //Edytowanie

    @RequestMapping(value = "/pracownik/edit/{id}")
    public String edytujPracownik(@PathVariable(value = "id") Long id, Model model)
    {
        Pracownik pracownik = pracownikService.getPracownikById(id);
        model.addAttribute("idPracownika", pracownik.getIdPracownika());
        model.addAttribute("imie", pracownik.getImie());
        model.addAttribute("nazwisko", pracownik.getNazwisko());
        model.addAttribute("telefon", pracownik.getTelefon());
        model.addAttribute("email", pracownik.getEmail());
        model.addAttribute("dataZa", pracownik.getDataZatrudnienia());
        model.addAttribute("dataZw", pracownik.getDataZwolnienia());
        model.addAttribute("miejs", pracownik.getMiejscowosc());
        model.addAttribute("ulica", pracownik.getUlica());
        model.addAttribute("nrm", pracownik.getNrMieszkania());
        model.addAttribute("nrp", pracownik.getNrPocztowy());
        model.addAttribute("stan", pracownik.getIdStanowsiska());
        model.addAttribute("konto", pracownik.getIdKonta());

        List<Stanowisko> stanowiska = stanowiskoService.getStanowisko();
        model.addAttribute("stanowiska", stanowiska);

        List<Konto> konta = kontoService.getKonta();
        model.addAttribute("konta", konta);

        return "editPracownik";
    }


    @RequestMapping(value = "/pracownik/edit/save", method = RequestMethod.POST)
    public String zapiszPracownik(@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "imie") String imie,
                                  @RequestParam(value = "nazwisko") String nazwisko,
                                  @RequestParam(value = "telefon") String telefon,
                                  @RequestParam(value = "email") String email,
                                  @RequestParam(value = "dataZa") String dataZa,
                                  @RequestParam(value = "dataZw") String dataZW,
                                  @RequestParam(value = "miejs") String miejscowosc,
                                  @RequestParam(value = "ulica") String ulica,
                                  @RequestParam(value = "nrm") String nr_mieszkania,
                                  @RequestParam(value = "nrp") String nr_pocztowy,
                                  @RequestParam(value = "stan") Long stan,
                                  @RequestParam(value = "konto") Long konto,
                              Model model)
    {
        pracownikService.savePracownik(id, imie, nazwisko, telefon, email, dataZa, dataZW, miejscowosc, ulica, nr_mieszkania, nr_pocztowy, stan, konto);
        return "redirect:/AwypiszP.html";
    }


    @ExceptionHandler
    public String handlerException(Model model, Exception exception)
    {
        String message = exception.getMessage();

        model.addAttribute("errormessage", message);
        return "errorpage";
    }

}
