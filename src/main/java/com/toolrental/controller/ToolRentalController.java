package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import com.toolrental.model.Tool;
import com.toolrental.repository.ToolRepository;

import java.time.LocalDate;

public class ToolRentalController {
    ToolRepository repository = new ToolRepository();

    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercent, LocalDate checkoutDate) {
        RentalAgreement rentalAgreement = new RentalAgreement();
        Tool rentedTool = repository.getTool(toolCode);
        rentalAgreement.setFinalCharge(rentedTool.getType().getDailyCost());
        return rentalAgreement;
    }

}
