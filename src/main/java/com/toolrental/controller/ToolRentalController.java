package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import com.toolrental.model.Tool;
import com.toolrental.model.ToolType;
import com.toolrental.repository.ToolRepository;
import com.toolrental.service.ToolRentalService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ToolRentalController {
    ToolRentalService service;

    public ToolRentalController(){
        service = new ToolRentalService();
    }

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        if(rentalDayCount < 1){
            throw new IllegalArgumentException("Please enter at least one day for renting tool");
        }
        if(discountPercent < 0 || discountPercent > 100){
            throw new IllegalArgumentException("Please enter a discount percent between 0 and 100");
        }

        RentalAgreement rentalAgreement = service.createRentalAgreement(toolCode, rentalDayCount, discountPercent, checkoutDate);

        return rentalAgreement;
    }
}
