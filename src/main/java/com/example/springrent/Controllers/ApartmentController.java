package com.example.springrent.Controllers;

import com.example.springrent.Models.Apartment;
import com.example.springrent.Models.Landlord;
import com.example.springrent.Repo.ApartmentRepository;
import com.example.springrent.Repo.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApartmentController {
    @Autowired
    public ApartmentRepository apartmentRepository;
    @Autowired
    public LandlordRepository landlordRepository;


    @GetMapping("/apartments/apartments")
    public String getApartments(Model model){
        Iterable<Apartment> apartments=apartmentRepository.findAll();
        model.addAttribute("apartments", apartments);
        return "/apartments/apartments";
    }
    @GetMapping("apartments/addApartment")
    public String addApartmentForm(Model model){
        Iterable<Landlord> landlords= landlordRepository.findAll();
        model.addAttribute("landlords", landlords);
        return "/apartments/addApartment";
    }
    @PostMapping("apartments/addApartment")
    public String addApartment(@RequestParam String title, @RequestParam int roomsAmount, @RequestParam String address,
                               @RequestParam float price, @RequestParam Long landlordId,Model model){
        Landlord landlord=landlordRepository.findById(landlordId).get();
        Apartment apartment=new Apartment(title,roomsAmount,address,price,landlord);
        apartmentRepository.save(apartment);

        return "redirect:/apartments/apartments";
    }
    @GetMapping("/apartments/{id}/info")
    public String infoApartment(@PathVariable(value = "id")Long id, Model model){
        if(!apartmentRepository.existsById(id)){
            return "redirect:/apartments/apartments";
        }
        else{
            Apartment apartment=apartmentRepository.findById(id).get();
            Landlord landlord=landlordRepository.findById(apartment.getLandlord().getId()).get();
            model.addAttribute("apartment", apartment);
            model.addAttribute("landlord", landlord);
            return "/apartments/infoApartment";
        }
    }
    @GetMapping("/apartments/{id}/deleteApartment")
    public String deleteApartment(@PathVariable(value = "id") Long id, Model model){
        Apartment apartment=apartmentRepository.findById(id).get();
        apartmentRepository.delete(apartment);
        return "redirect:/apartments/apartments";
    }

    @GetMapping("/apartments/{id}/edit")
    public String editApartmentForm(@PathVariable(value = "id")Long id, Model model){
        if(!apartmentRepository.existsById(id)){
            return "redirect:/apartments/apartments";
        }
        else{
            Apartment apartment=apartmentRepository.findById(id).get();
            Landlord landlord=landlordRepository.findById(apartment.getLandlord().getId()).get();
            model.addAttribute("apartment", apartment);
            model.addAttribute("landlord", landlord);
            Iterable<Landlord> landlords= landlordRepository.findAll();
            model.addAttribute("landlords", landlords);
            return "/apartments/editApartment";
        }
    }
    @PostMapping("/apartments/{id}/edit")
    public String editApartment(@PathVariable(value = "id")Long id,@RequestParam String title,
                                @RequestParam int roomsAmount, @RequestParam String address, @RequestParam float price,
                                @RequestParam Long landlordId, Model model){
        Apartment apartment=apartmentRepository.findById(id).get();
        apartment.setTitle(title);
        apartment.setRoomsAmount(roomsAmount);
        apartment.setAddress(address);
        apartment.setPrice(price);
        Landlord landlord=landlordRepository.findById(landlordId).get();
        apartment.setLandlord(landlord);
        apartmentRepository.save(apartment);

        return "redirect:/apartments/apartments";
    }
}
