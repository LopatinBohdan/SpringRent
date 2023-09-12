package com.example.springrent.Repo;

import com.example.springrent.Models.Rent;
import org.springframework.data.repository.CrudRepository;

public interface RentRepository extends CrudRepository<Rent,Long> { }
