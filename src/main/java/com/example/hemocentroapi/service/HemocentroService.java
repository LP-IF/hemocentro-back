package com.example.hemocentroapi.service;

import com.example.hemocentroapi.model.repository.HemocentroRepository;
import org.springframework.stereotype.Service;

@Service
public class HemocentroService {

    private HemocentroRepository repository;

    public HemocentroService(HemocentroRepository repository)  {
        this.repository = repository;
    }

}
