package org.generation.italy.projectPEPE.model.services;

import org.generation.italy.projectPEPE.model.abstractions.AbstractFoodStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PEPEToChange {

    private AbstractFoodStorageRepository repo;

    @Autowired
    public PEPEToChange(AbstractFoodStorageRepository repo) {
        this.repo = repo;
    }
}
