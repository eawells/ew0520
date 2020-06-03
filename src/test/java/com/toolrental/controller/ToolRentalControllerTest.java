package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ToolRentalControllerTest {

    @Test
    public void givenALadderRental_WhenRentingForOneWeekday_ThenTheCostIs199(){
        ToolRentalController controller = new ToolRentalController();

        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForOneWeekday_ThenTheCostIs149(){
        ToolRentalController controller = new ToolRentalController();

        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }
}
