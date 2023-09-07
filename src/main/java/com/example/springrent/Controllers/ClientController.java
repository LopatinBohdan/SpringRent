package com.example.springrent.Controllers;

import com.example.springrent.Repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository repository;
}
