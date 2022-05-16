package com.api.productionmanager.services;

import com.api.productionmanager.dtos.ProductDto;
import com.api.productionmanager.models.NecessaryMaterial;
import com.api.productionmanager.models.ProductModel;
import com.api.productionmanager.repositories.MaterialRepository;
import com.api.productionmanager.repositories.NecessaryMaterialRepository;
import com.api.productionmanager.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    NecessaryMaterialRepository necessaryMaterialRepository;
    @Autowired
    MaterialRepository materialRepository;

    @Transactional
    public ProductModel save(ProductModel productModel) {
        return productRepository.save(productModel);
        //necessaryMaterialRepository.saveAll(productModel.getMaterials());
    }

    public boolean existsByCode(String code) {
        return productRepository.existsByCode(code);
    }

    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    public Page<ProductModel> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<ProductModel> findById(int id) {
        return productRepository.findById(id);
    }

    @Transactional
    public void delete(ProductModel productModel) {
        productRepository.delete(productModel);
    }

    @Transactional
    public int saveDto(ProductDto productDto) {
        ProductModel productModel = new ProductModel();
        //BeanUtils.copyProperties(productDto, productModel);
        productModel.setPrice(productDto.getPrice());
        productModel.setName(productDto.getName());
        productModel.setCode(productDto.getCode());

        productRepository.save(productModel);

        necessaryMaterialRepository.saveAll(productDto.getMaterials().stream()
                .map(m ->
                        new NecessaryMaterial(
                                productModel,
                                materialRepository.getById(m.getMaterial()),
                                m.getNecessaryMaterial()
                        )).collect(Collectors.toSet()));

        return productModel.getId();
    }
}
