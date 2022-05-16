package com.api.productionmanager.controllers;

import com.api.productionmanager.dtos.MaterialDto;
import com.api.productionmanager.dtos.ProductDto;
import com.api.productionmanager.models.MaterialModel;
import com.api.productionmanager.models.NecessaryMaterial;
import com.api.productionmanager.models.ProductModel;
import com.api.productionmanager.services.MaterialService;
import com.api.productionmanager.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/production-manager")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(value = "/products")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto) {
        if (productService.existsByCode(productDto.getCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This code is already in use");
        }
        if (productService.existsByName(productDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This name is already in use");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);

//        productModel.setCode(productDto.getCode());
//        productModel.setName(productDto.getName());
//        productModel.setMaterials(new HashSet<NecessaryMaterial>());
//        productModel.getMaterials().add((NecessaryMaterial) productDto.getMaterials().toArray()[0]);
//        productModel.setPrice(productDto.getPrice());
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.findById(productService.saveDto(productDto)));
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Page<ProductModel>> getAllProducts(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(pageable));
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") int id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateOneProduct(@PathVariable(value = "id") int id, @RequestBody @Valid ProductDto productDto) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productDto, productModel);
        productModel.setId(productModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteOneProduct(@PathVariable(value = "id") int id) {
        Optional<ProductModel> productModelOptional = productService.findById(id);
        if (!productModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productService.delete(productModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
}
