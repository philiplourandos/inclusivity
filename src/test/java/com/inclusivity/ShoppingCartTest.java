package com.inclusivity;

import java.math.BigDecimal;
import java.util.List;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ShoppingCartTest {

    private static Product dove;
    private static Product axe;

    @BeforeAll
    public static void setup() {
        dove = new Product("dove", Money.of(39.99, "ZAR"));
        axe = new Product("axe", Money.of(99.99, "ZAR"));
    }

    @Test
    public void when_adding5ItemsToCart_itemCountAndTotalShouldBeCorrect() {
        final ShoppingCart cart = new ShoppingCart();
        List.of(dove, dove, dove, dove, dove).forEach(c -> cart.add(c));

        assertEquals(5, cart.getItemCount());

        final MonetaryAmount expectedTotal = Money.of(199.95, "ZAR");
        assertEquals(expectedTotal, cart.calculateTotalExVat());
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
