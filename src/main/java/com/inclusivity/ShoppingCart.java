package com.inclusivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private final List<Product> entries = new ArrayList();
    
    public void add(final Product additionalProduct) {
        entries.add(additionalProduct);
    }
    
    public int getItemCount() {
        return entries.size();
    }

    public BigDecimal calculateTotal() {
        final Optional<Double> priceSum = entries.stream()
            .map(Product::getPrice)
            .map(BigDecimal::doubleValue)
            .reduce((a, b) -> a + b);

        return priceSum.map(c -> new BigDecimal(c).setScale(2, RoundingMode.UP))
                    .orElseGet(() -> BigDecimal.ZERO);
    }
}
