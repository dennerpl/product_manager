package com.api.productionmanager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "MATERIALS")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MaterialModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "code", unique=true, nullable=false, updatable=true, length=100)
    private String code;

    @Column(name = "name", unique=true, nullable=false, updatable=true, length=150)
    private String name;

    @Column(name = "qtd", nullable=false, updatable=true)
    private Float qtd;

    @OneToMany(mappedBy="material", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<NecessaryMaterial> products = new HashSet<>();
}
