package com.api.productionmanager.repositories;

import com.api.productionmanager.models.NecessaryMaterial;
import com.api.productionmanager.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface NecessaryMaterialRepository extends JpaRepository<NecessaryMaterial, Integer> {
}
