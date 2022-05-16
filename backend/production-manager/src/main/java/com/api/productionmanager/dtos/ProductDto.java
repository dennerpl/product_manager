package com.api.productionmanager.dtos;

import com.api.productionmanager.models.MaterialModel;
import com.api.productionmanager.models.NecessaryMaterial;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class ProductDto {
    @NotBlank
    @Size(max=100)
    private String code;

    @NotBlank
    @Size(max=150)
    private String name;

    @NotNull
    private Float price;

    @NotNull
    private Set<NecessaryMaterialDto> materials;
}
