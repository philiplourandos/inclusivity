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

    private static final String NAME_DOVE = "Dove Soap";
    private static final MonetaryAmount DOVE_AMOUNT = Money.of(39.99, "ZAR");
    private static final String NAME_AXE = "Axe Deo's";
    private static final MonetaryAmount AXE_AMOUNT = Money.of(99.99, "ZAR");

    private static Product dove;
    private static Product axe;

    @BeforeAll
    public static void setup() {
        dove = new Product(NAME_DOVE, DOVE_AMOUNT);
        axe = new Product(NAME_AXE, AXE_AMOUNT);
    }

    @Test
    public void when_adding5ItemsToCart_itemCountAndTotalShouldBeCorrect() {
        final ShoppingCart cart = new ShoppingCart();
        List.of(dove, dove, dove, dove, dove).forEach(c -> cart.add(c));

        assertEquals(5, cart.getItemCount());
        cart.getEntries().stream().forEach(c -> {
            assertEquals(NAME_DOVE, c.getName());
            assertEquals(DOVE_AMOUNT, c.getPrice());
        });

        final MonetaryAmount expectedTotal = Money.of(199.95, "ZAR");
        assertEquals(expectedTotal, cart.calculateTotalExVat());
    }

    @Test
    public void when_shoppingCartHas5ItemsAnd3MoreOfTheSameItemsAdded_itemCountAndTotalShouldBeCorrect() {
        final ShoppingCart cart = new ShoppingCart();
        List.of(dove, dove, dove, dove, dove).forEach(c -> cart.add(c));

        assertEquals(5, cart.getItemCount());

        List.of(dove, dove, dove).forEach(c -> cart.add(c));

        assertEquals(8, cart.getItemCount());
        cart.getEntries().stream().forEach(c -> {
            assertEquals(NAME_DOVE, c.getName());
            assertEquals(DOVE_AMOUNT, c.getPrice());
        });

        assertEquals(Money.of(319.92, "ZAR"), cart.calculateTotalExVat());
    }

    @Test
    public void when_addingDifferentProductTypesToCartAndApplyingTax_totalShouldReflectTaxApplied() {
        fail();
    }
}
