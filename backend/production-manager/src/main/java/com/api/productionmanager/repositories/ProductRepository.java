package com.api.productionmanager.repositories;

import com.api.productionmanager.models.MaterialModel;
import com.api.productionmanager.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    boolean existsByCode(String code);

    boolean existsByName(String name);
}
