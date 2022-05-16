package com.api.productionmanager.controllers;

import com.api.productionmanager.dtos.MaterialDto;
import com.api.productionmanager.models.MaterialModel;
import com.api.productionmanager.services.MaterialService;
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
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/production-manager")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @PostMapping(value="/materials")
    public ResponseEntity<Object> saveMaterial(@RequestBody @Valid MaterialDto materialDto){
        if(materialService.existsByCode(materialDto.getCode())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This code is already in use");
        }
        if(materialService.existsByName(materialDto.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This name is already in use");
        }
        var materialModel = new MaterialModel();
        BeanUtils.copyProperties(materialDto, materialModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.save(materialModel));
    }

    @GetMapping(value="/materials")
    public ResponseEntity<Page<MaterialModel>> getAllMaterials(@PageableDefault(page=0, size=10, sort="id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(materialService.findAll(pageable));
    }

    @GetMapping(value="/materials/{id}")
    public ResponseEntity<Object> getOneMaterial(@PathVariable(value="id") int id) {
        Optional<MaterialModel> materialModelOptional = materialService.findById(id);
        if(!materialModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(materialModelOptional.get());
    }

    @PutMapping(value="/materials/{id}")
    public ResponseEntity<Object> updateOneMaterial(@PathVariable(value="id") int id, @RequestBody @Valid MaterialDto materialDto) {
        Optional<MaterialModel> materialModelOptional = materialService.findById(id);
        if(!materialModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found");
        }
        var materialModel = new MaterialModel();
        BeanUtils.copyProperties(materialDto, materialModel);
        materialModel.setId(materialModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(materialService.save(materialModel));
    }

    @DeleteMapping(value="/materials/{id}")
    public ResponseEntity<Object> deleteOneMaterial(@PathVariable(value="id") int id) {
        Optional<MaterialModel> materialModelOptional = materialService.findById(id);
        if(!materialModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Material not found");
        }
        materialService.delete(materialModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Material deleted successfully");
    }
}
