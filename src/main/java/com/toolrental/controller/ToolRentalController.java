package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import com.toolrental.model.Tool;
import com.toolrental.repository.ToolRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ToolRentalController {
    ToolRepository repository = new ToolRepository();

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        Tool rentedTool = repository.getTool(toolCode);

        BigDecimal total = rentedTool.getType().getDailyCost();

        if(discountPercent > 0){
            double percent = (double) discountPercent/100;
            BigDecimal discountAmount = total.multiply(new BigDecimal(percent)).setScale(2, RoundingMode.HALF_UP);
            total = total.subtract(discountAmount);
        }

        rentalAgreement.setFinalCharge(total);

        return rentalAgreement;
    }

}
