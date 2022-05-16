package com.api.productionmanager.dtos;

import com.api.productionmanager.models.NecessaryMaterial;
import com.api.productionmanager.models.ProductModel;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class MaterialDto {

    @NotBlank
    @Size(max=100)
    private String code;

    @NotBlank
    @Size(max=150)
    private String name;

    @NotNull
    private Float qtd;

    private Set<NecessaryMaterial> products;
}
