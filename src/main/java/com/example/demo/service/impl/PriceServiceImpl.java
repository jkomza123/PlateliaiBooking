package com.example.demo.service.impl;

import com.example.demo.service.PriceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class PriceServiceImpl implements PriceService {

    @Override
    public BigDecimal calculatePrice(
            BigDecimal nightsPrice,
            LocalDate start,
            LocalDate end
    ) {
        long nights = ChronoUnit.DAYS.between(start, end);

        if (nights <= 0) {
            throw new IllegalArgumentException("End date must be after start date");
        }

        BigDecimal nightsBd = BigDecimal.valueOf(nights);

        BigDecimal total = nightsPrice
                .multiply(nightsBd)
                .setScale(2, RoundingMode.HALF_UP);

        return total;
    }
}

