package Controllers;

import Repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ApartmentController {
    @Autowired
    private ApartmentRepository repository;


}
