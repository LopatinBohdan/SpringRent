package com.example.springrent.Repo;

import com.example.springrent.Models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> { }
