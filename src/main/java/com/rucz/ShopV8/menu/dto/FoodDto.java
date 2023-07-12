package com.rucz.ShopV8.menu.dto;

import com.rucz.ShopV8.menu.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private BigDecimal price;

    private String description;

    private boolean inStock;

    private String image;

    private Category category;

    private boolean isActivated;

    private boolean deleted;

    private boolean recommended;
}
