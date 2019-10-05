package com.inclusivity;

import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ShoppingCartTest {

    private static Product dove;
    private static Product axe;

    @BeforeAll
    public static void setup() {
        dove = Product.builder().name("Dove").price(new BigDecimal("39.99")).build();
        axe = Product.builder().name("Axe").price(new BigDecimal("99.99"))
    }

    @Test
    public void when_adding5ItemsToCart_itemCountAndTotalShouldBeCorrect() {
        fail();
    }

    @Test
    public void when_shoppingCartHas5ItemsAnd3MoreOfTheSameItemsAdded_itemCountAndTotalShouldBeCorrect() {
        fail();
    }

    @Test
    public void when_addingDifferentProductTypesToCartAndApplyingTax_totalShouldReflectTaxApplied() {
        fail();
    }
}
