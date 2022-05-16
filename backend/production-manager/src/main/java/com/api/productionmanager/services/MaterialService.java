package com.api.productionmanager.services;

import com.api.productionmanager.models.MaterialModel;
import com.api.productionmanager.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Transactional
    public MaterialModel save(MaterialModel materialModel) {
            return materialRepository.save(materialModel);
    }

    public boolean existsByCode(String code) {
        return materialRepository.existsByCode(code);
    }

    public boolean existsByName(String name) {
        return materialRepository.existsByName(name);
    }

    public Page<MaterialModel> findAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    public Optional<MaterialModel> findById(int id) {
        return materialRepository.findById(id);
    }

    @Transactional
    public void delete(MaterialModel materialModel) {
        materialRepository.delete(materialModel);
    }
}
