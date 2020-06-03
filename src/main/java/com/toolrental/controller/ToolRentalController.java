package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ToolRentalController {

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        rentalAgreement.setFinalCharge(new BigDecimal("1.99"));
        return rentalAgreement;
    }

}
