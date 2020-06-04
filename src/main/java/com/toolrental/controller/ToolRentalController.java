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

        int chargeableDays = 0;
        for(int i = 1; i <= rentalDayCount; i++){
            if(isWeekday(checkoutDate.plusDays(i))){
                chargeableDays++;
            }
        }
        rentalAgreement.setChargeableDays(chargeableDays);

        BigDecimal total = rentedTool.getType().getDailyCost().multiply(new BigDecimal(rentalDayCount));
        if(discountPercent > 0){
            double percent = (double) discountPercent/100;
            BigDecimal discountAmount = total.multiply(new BigDecimal(percent)).setScale(2, RoundingMode.HALF_UP);
            total = total.subtract(discountAmount);
        }

        rentalAgreement.setFinalCharge(total);

        return rentalAgreement;
    }

    private boolean isWeekday(LocalDate date){
        if(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7){
            return false;
        }
        return true;
    }
}
