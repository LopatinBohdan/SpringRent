package com.example.springrent.Controllers;

import com.example.springrent.Models.Apartment;
import com.example.springrent.Models.Client;
import com.example.springrent.Models.Landlord;
import com.example.springrent.Repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("clients/clients")
    public String getClients(Model model){
        Iterable<Client> clients=clientRepository.findAll();
        model.addAttribute("clients", clients );
        return"/clients/clients";
    }

    @GetMapping("clients/addClient")
    public String addClientForm(Model model){
        return "/clients/addClient";
    }

    @PostMapping("clients/addClient")
    public String addClient(@RequestParam String name, @RequestParam String phone,
                               @RequestParam int roomAmount, Model model){
        Client client=new Client(name,phone,roomAmount);
        clientRepository.save(client);
        return "redirect:/clients/clients";
    }

    @GetMapping("/clients/{id}/delete")
    public String clientDelete(@PathVariable(value = "id") Long clientId, Model model){
        Client client=clientRepository.findById(clientId).get();
        clientRepository.delete(client);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/{id}/edit")
    public String clientEditForm(@PathVariable(value = "id")Long clientId, Model model){
        if(!clientRepository.existsById(clientId)){
            return "redirect:clients/clients";
        }
        else{
            Client client=clientRepository.findById(clientId).get();
            model.addAttribute("client", client);
            return "clients/editClient";
        }
    }
    @PostMapping("clients/{id}/edit")
    public String clientEdit(@PathVariable(value = "id") Long clientId,@RequestParam String name,
                             @RequestParam String phone,
                             @RequestParam int roomAmount, Model model){
        Client client=clientRepository.findById(clientId).get();
        client.setName(name);
        client.setPhone(phone);
        client.setRoomAmount(roomAmount);
        clientRepository.save(client);
        return "redirect:/clients/clients";
    }

    @GetMapping("clients/{id}/info")
    public String clientInfo(@PathVariable(value = "id")Long id, Model model){
        if(!clientRepository.existsById(id)){
            return "redirect:clients/clients";
        }
        else{
            Client client=clientRepository.findById(id).get();
            model.addAttribute("client", client);
            return "clients/infoClient";
        }
    }

}
