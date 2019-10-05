package com.inclusivity;

import java.math.BigDecimal;
import java.util.List;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShoppingCartTest {

    private static final String NAME_DOVE = "Dove Soap";
    private static final MonetaryAmount DOVE_AMOUNT = Money.of(39.99, "ZAR");
    private static final String NAME_AXE = "Axe Deo's";
    private static final MonetaryAmount AXE_AMOUNT = Money.of(99.99, "ZAR");

    private static final double VAT = 12.5;

    private static Product dove;
    private static Product axe;

    @BeforeClass
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
        final ShoppingCart cart = new ShoppingCart();
        List.of(dove, dove, axe, axe).forEach(c -> cart.add(c));

        assertEquals(4, cart.getItemCount());

        cart.getEntries().stream().limit(2).forEach(c -> {
            assertEquals(NAME_DOVE, c.getName());
            assertEquals(DOVE_AMOUNT, c.getPrice());
        });

        cart.getEntries().stream().skip(2).forEach(c -> {
            assertEquals(NAME_AXE, c.getName());
            assertEquals(AXE_AMOUNT, c.getPrice());
        });

        final MonetaryAmount totalIncludingVat = cart.calculateTotalWithVat(VAT);
        assertEquals(Money.of(314.96, "ZAR"), totalIncludingVat);
    }
}
