package com.rucz.ShopV8.menu.model;

import com.rucz.ShopV8.admin.model.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    private Food food;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    private Admin admin;

    private int quantity;

    private BigDecimal price;

    private BigDecimal totalPrice;

    boolean active = true;
}
