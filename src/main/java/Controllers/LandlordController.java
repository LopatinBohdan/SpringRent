package Controllers;

import Repo.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LandlordController {
    @Autowired
    private LandlordRepository repository;
}
