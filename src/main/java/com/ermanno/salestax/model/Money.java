package com.ermanno.salestax.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Using Fowler's Money pattern to handle currency. Could be extended to handle multiple currencies (USD, EUR, ...).
 * Example in PHP: https://dzone.com/articles/practical-php-patterns/basic/practical-php-patterns-money).
 * @author ermanno
 *
 */
public class Money implements Comparable<Money> {
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_UP;
    private static final int scale = 2;

    private BigDecimal amount;
    
    public Money(String amount) {
        this(new BigDecimal(amount), DEFAULT_ROUNDING);
    }
    
    public Money(BigDecimal amount) {
        this(amount, DEFAULT_ROUNDING);
    }

    public Money(BigDecimal amount, RoundingMode rounding) {
        this.amount = amount;
        this.amount = amount.setScale(scale, rounding); // two decimal places to the right of the decimal point
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    public Money add(Money money) {
        amount.add(money.amount);
        return this;
    }
    
    public Money multiply(Money money) {
        amount.multiply(money.amount);
        return this;
    }
    
    public boolean isNotAPositiveAmount() {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }
    
    public Money percentage(BigDecimal rate) {
        return new Money(amount.multiply(rate));
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.amount);
    }

    @Override
    public int compareTo(Money money) {
        return amount.compareTo(money.amount);
    }
}
