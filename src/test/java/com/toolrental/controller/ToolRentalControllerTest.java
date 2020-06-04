package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ToolRentalControllerTest {
    private ToolRentalController controller;

    @Before
    public void setUp(){
        controller = new ToolRentalController();
    }

    @Test
    public void givenALadderRental_WhenRentingForOneWeekday_ThenTheCostIs199Cents(){
        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingForOneWeekday_ThenTheCostIs149Cents(){
        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("CHNS", 1, 0, date);

        assertEquals(new BigDecimal("1.49"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForOneWeekday_ThenTheCostIs299Cents(){
        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenDiscountIs10Percent_ThenTheCostIs179Cents(){
        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("LADW", 1, 10, date);

        assertEquals(new BigDecimal("1.79"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenDiscountIs10Percent_ThenTheCostIs134Cents(){
        LocalDate date = LocalDate.of(2020, 05, 28);
        RentalAgreement actual = controller.checkout("CHNS", 1, 10, date);

        assertEquals(new BigDecimal("1.34"), actual.getFinalCharge());
    }
}
