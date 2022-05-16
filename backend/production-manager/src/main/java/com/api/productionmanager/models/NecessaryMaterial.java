package com.api.productionmanager.models;

import com.api.productionmanager.embeddables.NecessaryMaterialKey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class NecessaryMaterial {
    public NecessaryMaterial(ProductModel product, MaterialModel material, Float necessaryMaterial) {
        this.id = new NecessaryMaterialKey(product.getId(), material.getId());
        this.product = product;
        this.material = material;
        this.necessaryMaterial = necessaryMaterial;
    }

    @EmbeddedId
            @EqualsAndHashCode.Include
    NecessaryMaterialKey id = new NecessaryMaterialKey();

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "product_id")
    @JsonIgnore
    ProductModel product;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "material_id")
    MaterialModel material;

    Float necessaryMaterial;

    public NecessaryMaterialKey getId() {
        return id;
    }

    public void setId(NecessaryMaterialKey id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public MaterialModel getMaterial() {
        return material;
    }

    public void setMaterial(MaterialModel material) {
        this.material = material;
    }

    public Float getNecessaryMaterial() {
        return necessaryMaterial;
    }

    public void setNecessaryMaterial(Float necessaryMaterial) {
        this.necessaryMaterial = necessaryMaterial;
    }
}
