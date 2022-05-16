package com.api.productionmanager.embeddables;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class NecessaryMaterialKey implements Serializable {
    @Column(name = "product_id")
    Integer productId;

    @Column(name = "material_id")
    Integer materialId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NecessaryMaterialKey)) return false;
        NecessaryMaterialKey key = (NecessaryMaterialKey) o;
        return Objects.equals(productId, key.productId) &&
                Objects.equals(materialId, key.materialId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productId, materialId);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
}
