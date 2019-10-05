package com.inclusivity;

import javax.money.MonetaryAmount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Product {
    private String name;

    private MonetaryAmount price;
}
