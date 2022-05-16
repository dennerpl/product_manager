package com.api.productionmanager.repositories;

import com.api.productionmanager.models.MaterialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MaterialRepository extends JpaRepository<MaterialModel, Integer> {

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
