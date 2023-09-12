package com.example.springrent.Controllers;

import com.example.springrent.Models.Apartment;
import com.example.springrent.Models.Client;
import com.example.springrent.Models.Rent;
import com.example.springrent.Repo.ApartmentRepository;
import com.example.springrent.Repo.ClientRepository;
import com.example.springrent.Repo.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class RentController {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private ApartmentRepository apartmentRepository;
    @Autowired
    private ClientRepository clientRepository;


    @GetMapping("/rents/rents")
    public String getRents(Model model){
        Iterable<Rent> rents =rentRepository.findAll();
        model.addAttribute("rents", rents);
        return "/rents/rents";
    }

    @GetMapping("/rents/addRent")
    public String addRentForm(Model model){
        Iterable<Apartment> apartments=apartmentRepository.findAll();
        Iterable<Client> clients=clientRepository.findAll();
        model.addAttribute("apartments", apartments);
        model.addAttribute("clients", clients);

        return"/rents/addRent";
    }
    @PostMapping("/rents/addRent")
    public String addRent(@RequestParam String start, @RequestParam String finish,
                          @RequestParam Long apartmentId, @RequestParam Long clientId, Model model){
        Apartment apartment=apartmentRepository.findById(apartmentId).get();
        Client client=clientRepository.findById(clientId).get();
        LocalDate rentStart=LocalDate.parse(start);
        LocalDate rentFinish=LocalDate.parse(finish);
        Rent rent=new Rent(rentStart,rentFinish,apartment,client);
        rentRepository.save(rent);

        return "redirect:/rents/rents";
    }

    @GetMapping("/rents/{id}/info")
    public String infoRent(@PathVariable(value = "id")Long id, Model model){
        if(!rentRepository.existsById(id)){
            return "redirect:/rents/rents";
        }
        else{
            Rent rent=rentRepository.findById(id).get();
            Apartment apartment=apartmentRepository.findById(rent.getApartment().getId()).get();
            Client client=clientRepository.findById(rent.getClient().getId()).get();
            model.addAttribute("rent", rent);
            model.addAttribute("apartment", apartment);
            model.addAttribute("client", client);

            return "rents/infoRent";
        }
    }

    @GetMapping("/rents/{id}/edit")
    public String editRentForm(@PathVariable(value = "id") Long id, Model model){
        if(!rentRepository.existsById(id)){
            return "redirect:/rents/rents";
        }
        else{
            Rent rent=rentRepository.findById(id).get();
            model.addAttribute("rent", rent);
            Iterable<Apartment> apartments=apartmentRepository.findAll();
            Iterable<Client> clients=clientRepository.findAll();
            model.addAttribute("apartments", apartments);
            model.addAttribute("clients", clients);

            return "/rents/editRent";
        }
    }
    @PostMapping("/rents/{id}/edit")
    public String editRent(@PathVariable(value = "id")Long id, @RequestParam String start, @RequestParam String finish,
                           @RequestParam Long apartmentId, @RequestParam Long clientId, Model model){
        Rent rent=rentRepository.findById(id).get();
        rent.setStart(LocalDate.parse(start));
        rent.setFinish(LocalDate.parse(finish));
        Apartment apartment=apartmentRepository.findById(apartmentId).get();
        rent.setApartment(apartment);
        Client client=clientRepository.findById(clientId).get();
        rent.setClient(client);
        rentRepository.save(rent);

        return "redirect:/rents/rents";
    }

    @GetMapping("/rents/{id}/delete")
    public String deleteRent(@PathVariable(value = "id") Long id, Model model){
        Rent rent=rentRepository.findById(id).get();
        rentRepository.delete(rent);
        return "redirect:/rents/rents";
    }

}
