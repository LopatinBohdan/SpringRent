package com.example.springrent.Controllers;

import com.example.springrent.Models.Apartment;
import com.example.springrent.Models.Client;
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

import java.util.*;

@Controller
public class LandlordController {
    @Autowired
    private LandlordRepository landlordRepository;
    private ApartmentRepository apartmentRepository;
    @GetMapping("landlords/landlords")
    public String getClients(Model model){
        Iterable<Landlord> landlords=landlordRepository.findAll();
        model.addAttribute("landlords", landlords );
        return"/landlords/landlords";
    }
    @GetMapping("landlords/addLandlord")
    public String addClientForm(Model model){
        return "/landlords/addLandlord";
    }

    @PostMapping("landlords/addLandlord")
    public String addClient(@RequestParam String name, @RequestParam String phone,Model model){
        Landlord landlord=new Landlord(name, phone);
        landlordRepository.save(landlord);
        return "redirect:/landlords/landlords";
    }
    @GetMapping("landlords/{id}/info")
    public String clientInfo(@PathVariable(value = "id")Long id, Model model){
        if(!landlordRepository.existsById(id)){
            return "redirect:landlords/landlords";
        }
        else{
            Landlord landlord=landlordRepository.findById(id).get();
           // Iterable<Apartment> apartments=apartmentRepository.findAll();
//            if(apartments!=null){
//                ArrayList<Apartment> landlordApartments=new ArrayList<>();
//                for (Apartment item: apartments) {
//                    if (item.getLandlord().getId() == landlord.getId()) {
//                        landlordApartments.add(item);
//                    }
//                }
//                model.addAttribute("apartments", landlordApartments);
//            }
            model.addAttribute("landlord", landlord);

            return "landlords/infoLandlord";
        }
    }

    @GetMapping("/landlords/{id}/edit")
    public String landlordEditForm(@PathVariable(value = "id")Long id, Model model){
        if(!landlordRepository.existsById(id)){
            return "redirect:/landlords/landlords";
        }
        else{
            Landlord landlord=landlordRepository.findById(id).get();
            model.addAttribute("landlord", landlord);
            return "/landlords/editLandlord";
        }
    }
    @PostMapping("/landlords/{id}/edit")
    public String landlordEdit(@PathVariable(value = "id") Long id,@RequestParam String name,
                             @RequestParam String phone, Model model){
        Landlord landlord=landlordRepository.findById(id).get();
        landlord.setName(name);
        landlord.setPhone(phone);
        landlordRepository.save(landlord);
        return "redirect:/landlords/landlords";
    }
    @GetMapping("/landlords/{id}/delete")
    public String landlordDelete(@PathVariable(value = "id") Long id, Model model){
        Landlord landlord=landlordRepository.findById(id).get();
        landlordRepository.delete(landlord);
        return "redirect:/landlords/landlords";
    }
}
