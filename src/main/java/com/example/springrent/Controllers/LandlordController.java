package com.example.springrent.Controllers;

import com.example.springrent.Repo.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LandlordController {
    @Autowired
    private LandlordRepository repository;
}
