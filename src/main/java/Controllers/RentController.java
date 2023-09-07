package Controllers;

import Repo.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class RentController {
    @Autowired
    private RentRepository repository;
}
