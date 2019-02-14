package com.nogueira.krusty.krab.krustykrab.services;

import com.nogueira.krusty.krab.krustykrab.repositories.IngredienteRepository;
import com.nogueira.krusty.krab.krustykrab.repositories.LancheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancheService {

    @Autowired
    private LancheRepository lancheRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;
}
