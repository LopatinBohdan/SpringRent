package com.example.springrent.Controllers;

import com.example.springrent.Models.Apartment;
import com.example.springrent.Repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    public ApartmentRepository repository;
    @GetMapping("/")
    public String home(Model model){
        Iterable<Apartment> apartmentList=repository.findAll();
        model.addAttribute("apartment", apartmentList);
        return "index";
    }


}
