package com.example.springrent.Repo;

import com.example.springrent.Models.Apartment;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepository extends CrudRepository<Apartment, Long> { }
