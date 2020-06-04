package com.toolrental.controller;

import com.toolrental.model.RentalAgreement;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ToolRentalControllerTest {
    private ToolRentalController controller;
    private LocalDate date;

    @Before
    public void setUp(){
        controller = new ToolRentalController();
        date = LocalDate.of(2020, 05, 27);
    }

    @Test
    public void givenALadderRental_WhenRentingForOneWeekday_ThenTheCostIs199Cents(){
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingForOneWeekday_ThenTheCostIs149Cents(){
        RentalAgreement actual = controller.checkout("CHNS", 1, 0, date);

        assertEquals(new BigDecimal("1.49"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForOneWeekday_ThenTheCostIs299Cents(){
        RentalAgreement actual = controller.checkout("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenDiscountIs10Percent_ThenTheCostIs179Cents(){
        RentalAgreement actual = controller.checkout("LADW", 1, 10, date);

        assertEquals(new BigDecimal("1.79"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenDiscountIs10Percent_ThenTheCostIs134Cents(){
        RentalAgreement actual = controller.checkout("CHNS", 1, 10, date);

        assertEquals(new BigDecimal("1.34"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenDiscountIs15Percent_ThenTheCostIs254Cents(){
        RentalAgreement actual = controller.checkout("JAKD", 1, 15, date);

        assertEquals(new BigDecimal("2.54"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingFor2Weekdays_ThenTheCostIs598Cents(){
        RentalAgreement actual = controller.checkout("JAKD", 2, 0, date);

        assertEquals(new BigDecimal("5.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingForWeekend_ThenChargeableDaysIs0(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("CHNS", 2, 0, date);

        assertEquals(0, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor1WeekdayAndWeekend_ThenChargeableDaysIs1(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("CHNS", 3, 0, date);

        assertEquals(1, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor2WeekdaysAndWeekend_ThenChargeableDaysIs2(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("CHNS", 4, 0, date);

        assertEquals(2, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor2WeekdaysAndWeekend_ThenCostIs298Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("CHNS", 4, 0, date);

        assertEquals(new BigDecimal("2.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor1Weekday_ThenRentalAgreementContainsAllFields(){
        RentalAgreement expected = new RentalAgreement("CHNS", "Chainsaw", "Stihl",
                1, date, date.plusDays(1), new BigDecimal("1.49"), 1, new BigDecimal("1.49"),
                0, new BigDecimal("0"), new BigDecimal("1.49"));

        RentalAgreement actual = controller.checkout("CHNS", 1, 0, date);

        assertEquals(expected, actual);
    }

    @Test
    public void givenALadderRental_WhenRentingFor1DayOfWeekend_ThenTheCostIs199Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingFor2DaysOfWeekend_ThenTheCostIs398Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = controller.checkout("LADW", 2, 0, date);

        assertEquals(new BigDecimal("3.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForJuly4th_ThenTheCostIs0(){
        date = LocalDate.of(2019, 07, 03);
        RentalAgreement actual = controller.checkout("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForFridayJuly3rd_ThenTheCostIs0(){
        date = LocalDate.of(2020, 07, 02);
        RentalAgreement actual = controller.checkout("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingFor1WeekdayAndHoliday_ThenTheCostIs299(){
        date = LocalDate.of(2020, 07, 01);
        RentalAgreement actual = controller.checkout("JAKR", 2, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForJuly4th_ThenTheCostIs0(){
        date = LocalDate.of(2019, 07, 03);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForMondayJuly5th_ThenTheCostIs0(){
        date = LocalDate.of(2021, 07, 04);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForLaborDay_ThenTheCostIs0(){
        date = LocalDate.of(2020, 9, 6);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingFor1MondayInSeptember_ThenTheCostIs199(){
        date = LocalDate.of(2020, 9, 13);
        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }
}
