package com.example.demo.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PriceService {
    BigDecimal calculatePrice(BigDecimal nightsPrice, LocalDate start, LocalDate end);
}
