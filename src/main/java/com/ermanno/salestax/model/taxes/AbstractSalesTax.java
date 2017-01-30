package com.ermanno.salestax.model.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.ermanno.salestax.model.Item;
import com.ermanno.salestax.model.Money;

public abstract class AbstractSalesTax implements Tax {
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.UP;
    private static final BigDecimal DEFAULT_INCREMENT = new BigDecimal("0.05");
    
    protected Money round(Money money, BigDecimal increment, RoundingMode roundingMode) {
        BigDecimal divided = money.getAmount().divide(increment, 0, roundingMode);
        BigDecimal result = divided.multiply(increment);
        return new Money(result);
    }
    
    protected abstract Money calculateSalesTax(Item item);
    
    @Override
    public Money calculateTax(Item item) {
        return round(calculateSalesTax(item), DEFAULT_INCREMENT, DEFAULT_ROUNDING);
    }
}
