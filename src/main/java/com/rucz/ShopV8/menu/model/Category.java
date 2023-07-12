package com.rucz.ShopV8.menu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotEmpty
    private String name;

    private boolean activated = true;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;                           //TODO: fix image size limitation

    private boolean deleted = false;

    public Category(String name){
        this.name = name;
    }
}
