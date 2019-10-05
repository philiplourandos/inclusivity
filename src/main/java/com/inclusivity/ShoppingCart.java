package com.inclusivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.money.MonetaryAmount;
import org.javamoney.moneta.Money;

public class ShoppingCart {
    private final List<Product> entries = new ArrayList();
    
    public void add(final Product additionalProduct) {
        entries.add(additionalProduct);
    }
    
    public int getItemCount() {
        return entries.size();
    }

    public List<Product> getEntries() {
        return entries;
    }

    public MonetaryAmount calculateTotalExVat() {
        return entries.stream()
            .map(Product::getPrice)
            .reduce(Money.of(0.00, "ZAR"), (subTotal, element) -> subTotal.add(element));

    }
}
