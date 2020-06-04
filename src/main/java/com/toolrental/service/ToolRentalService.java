package com.toolrental.service;

import com.toolrental.model.RentalAgreement;
import com.toolrental.model.Tool;
import com.toolrental.model.ToolType;
import com.toolrental.repository.ToolRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class ToolRentalService {
    ToolRepository repository;


    public ToolRentalService(){
        repository = new ToolRepository();
    }

    public RentalAgreement createRentalAgreement(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate){
        RentalAgreement rentalAgreement = new RentalAgreement();

        rentalAgreement.setToolCode(toolCode);
        Tool rentedTool = repository.getTool(toolCode);

        rentalAgreement.setToolType(rentedTool.getType().getType());
        rentalAgreement.setToolBrand(rentedTool.getBrand());
        rentalAgreement.setRentalDays(rentalDayCount);
        rentalAgreement.setCheckoutDate(checkoutDate);
        rentalAgreement.setDueDate(checkoutDate.plusDays(rentalDayCount));

        int chargeableDays = calculateChargeableDays(rentalDayCount, checkoutDate, rentedTool.getType());
        rentalAgreement.setChargeableDays(chargeableDays);

        BigDecimal dailyCost = rentedTool.getType().getDailyCost();
        rentalAgreement.setDailyCharge(dailyCost);

        BigDecimal total = dailyCost.multiply(new BigDecimal(chargeableDays));
        rentalAgreement.setPreDiscountCharge(total);

        rentalAgreement.setDiscountPercent(discountPercent);
        BigDecimal discountAmount = calculateDiscount(discountPercent, total);
        rentalAgreement.setDiscountAmount(discountAmount);

        total = total.subtract(discountAmount);
        rentalAgreement.setFinalCharge(total);

        return rentalAgreement;
    }

    private int calculateChargeableDays(int rentalDayCount, LocalDate checkoutDate, ToolType toolType) {
        int chargeableDays = rentalDayCount;
        for(int i = 1; i <= rentalDayCount; i++){
            LocalDate currentDate = checkoutDate.plusDays(i);

            if(!toolType.isWeekendChargeable() && isWeekend(currentDate)){
                chargeableDays--;
            }
            else if(!toolType.isHolidayChargeable() && isHoliday(currentDate)){
                chargeableDays--;
            }
            else if(!toolType.isWeekdayChargeable() && !isWeekend(currentDate)){
                chargeableDays--;
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

    private boolean isWeekend(LocalDate date){
        int saturday = 6;
        int sunday = 7;

        if(date.getDayOfWeek().getValue() == saturday || date.getDayOfWeek().getValue() == sunday){
            return true;
        }
        return false;
    }

    private boolean isHoliday(LocalDate date) {
        int july = 7;
        int september = 9;
        int monday = 1;
        int friday = 5;

        if(date.getMonth().getValue() == july){
            if(date.getDayOfMonth() == 4 && !isWeekend(date)){
                return true;
            }
            if(date.getDayOfMonth() == 3 && date.getDayOfWeek().getValue() == friday){
                return true;
            }
            if(date.getDayOfMonth() == 5 && date.getDayOfWeek().getValue() == monday){
                return true;
            }
        }

        if(date.getMonth().getValue() == september && date.getDayOfWeek().getValue() == monday && date.getDayOfMonth() <= 7){
            return true;
        }

        return false;
    }
}
