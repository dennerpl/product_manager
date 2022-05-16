package com.api.productionmanager.services;

import com.api.productionmanager.models.NecessaryMaterial;
import com.api.productionmanager.repositories.NecessaryMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class NecessaryMaterialService {
    @Autowired
    NecessaryMaterialRepository necessaryMaterialRepository;

    @Transactional
    public NecessaryMaterial save(NecessaryMaterial necessaryMaterial) {
        return necessaryMaterialRepository.save(necessaryMaterial);
    }

    public Page<NecessaryMaterial> findAll(Pageable pageable) {
        return necessaryMaterialRepository.findAll(pageable);
    }

    public Optional<NecessaryMaterial> findById(int id) {
        return necessaryMaterialRepository.findById(id);
    }

    @Transactional
    public void delete(NecessaryMaterial necessaryMaterial) {
        necessaryMaterialRepository.delete(necessaryMaterial);
    }
}
