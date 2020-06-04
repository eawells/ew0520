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

        int chargeableDays = calculateChargeableDays(rentalDayCount, checkoutDate);
        rentalAgreement.setChargeableDays(chargeableDays);

        BigDecimal dailyCost = rentedTool.getType().getDailyCost();
        BigDecimal total = dailyCost.multiply(new BigDecimal(chargeableDays));

        BigDecimal discountAmount = calculateDiscount(discountPercent, total);

        total = total.subtract(discountAmount);
        rentalAgreement.setFinalCharge(total);

        return rentalAgreement;
    }

    private int calculateChargeableDays(int rentalDayCount, LocalDate checkoutDate) {
        int chargeableDays = 0;
        for(int i = 1; i <= rentalDayCount; i++){
            if(isWeekday(checkoutDate.plusDays(i))){
                chargeableDays++;
            }
        }
        return chargeableDays;
    }

    private BigDecimal calculateDiscount(int discountPercent, BigDecimal total) {
        BigDecimal discountAmount = new BigDecimal("0");
        if(discountPercent > 0){
            double percent = (double) discountPercent/100;
            discountAmount = total.multiply(new BigDecimal(percent)).setScale(2, RoundingMode.HALF_UP);
        }
        return discountAmount;
    }

    private boolean isWeekday(LocalDate date){
        if(date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7){
            return false;
        }
        return true;
    }
}
