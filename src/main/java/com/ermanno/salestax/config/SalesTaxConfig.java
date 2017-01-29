package com.ermanno.salestax.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ermanno.salestax.model.taxes.BasicSalesTax;
import com.ermanno.salestax.model.taxes.ImportDutySalesTax;
import com.ermanno.salestax.model.taxes.Tax;
import com.ermanno.salestax.services.TaxCalculator;
import com.ermanno.salestax.services.TaxCalculatorImpl;

@Configuration
@ComponentScan(basePackages = "com.ermanno.salestax.services")
public class SalesTaxConfig {
    
    @Bean
    public TaxCalculator taxCalculator() {
        List<Tax> taxes = new ArrayList<>();
        taxes.add(new BasicSalesTax());
        taxes.add(new ImportDutySalesTax());
        return new TaxCalculatorImpl(taxes);
    }
    
}
