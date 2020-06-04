package com.toolrental.service;

import com.toolrental.model.RentalAgreement;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ToolRentalServiceTest {
    private ToolRentalService service;
    private LocalDate date;

    @Before
    public void setUp(){
        service = new ToolRentalService();
        date = LocalDate.of(2020, 05, 27);
    }

    @Test
    public void givenALadderRental_WhenRentingFor1Weekday_ThenTheCostIs199Cents(){
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor1Weekday_ThenTheCostIs149Cents(){
        RentalAgreement actual = service.createRentalAgreement("CHNS", 1, 0, date);

        assertEquals(new BigDecimal("1.49"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingFor1Weekday_ThenTheCostIs299Cents(){
        RentalAgreement actual = service.createRentalAgreement("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenDiscountIs10Percent_ThenTheCostIs179Cents(){
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 10, date);

        assertEquals(new BigDecimal("1.79"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenDiscountIs10Percent_ThenTheCostIs134Cents(){
        RentalAgreement actual = service.createRentalAgreement("CHNS", 1, 10, date);

        assertEquals(new BigDecimal("1.34"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenDiscountIs15Percent_ThenTheCostIs254Cents(){
        RentalAgreement actual = service.createRentalAgreement("JAKD", 1, 15, date);

        assertEquals(new BigDecimal("2.54"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingFor2Weekdays_ThenTheCostIs598Cents(){
        RentalAgreement actual = service.createRentalAgreement("JAKD", 2, 0, date);

        assertEquals(new BigDecimal("5.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingForWeekend_ThenChargeableDaysIs0(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("CHNS", 2, 0, date);

        assertEquals(0, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor1WeekdayAndWeekend_ThenChargeableDaysIs1(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("CHNS", 3, 0, date);

        assertEquals(1, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor2WeekdaysAndWeekend_ThenChargeableDaysIs2(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("CHNS", 4, 0, date);

        assertEquals(2, actual.getChargeableDays());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor2WeekdaysAndWeekend_ThenCostIs298Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("CHNS", 4, 0, date);

        assertEquals(new BigDecimal("2.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRental_WhenRentingFor1Weekday_ThenRentalAgreementContainsAllFields(){
        RentalAgreement expected = new RentalAgreement("CHNS", "Chainsaw", "Stihl",
                1, date, date.plusDays(1), new BigDecimal("1.49"), 1, new BigDecimal("1.49"),
                0, new BigDecimal("0"), new BigDecimal("1.49"));

        RentalAgreement actual = service.createRentalAgreement("CHNS", 1, 0, date);

        assertEquals(expected, actual);
    }

    @Test
    public void givenALadderRental_WhenRentingFor1DayOfWeekend_ThenTheCostIs199Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingFor2DaysOfWeekend_ThenTheCostIs398Cents(){
        date = LocalDate.of(2020, 05, 29);
        RentalAgreement actual = service.createRentalAgreement("LADW", 2, 0, date);

        assertEquals(new BigDecimal("3.98"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForJuly4th_ThenTheCostIs0(){
        date = LocalDate.of(2019, 07, 03);
        RentalAgreement actual = service.createRentalAgreement("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingForFridayJuly3rd_ThenTheCostIs0(){
        date = LocalDate.of(2020, 07, 02);
        RentalAgreement actual = service.createRentalAgreement("JAKR", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRental_WhenRentingFor1WeekdayAndHoliday_ThenTheCostIs299(){
        date = LocalDate.of(2020, 07, 01);
        RentalAgreement actual = service.createRentalAgreement("JAKR", 2, 0, date);

        assertEquals(new BigDecimal("2.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForJuly4th_ThenTheCostIs0(){
        date = LocalDate.of(2019, 07, 03);
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForMondayJuly5th_ThenTheCostIs0(){
        date = LocalDate.of(2021, 07, 04);
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingForLaborDay_ThenTheCostIs0(){
        date = LocalDate.of(2020, 9, 6);
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("0.00"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRental_WhenRentingFor1MondayInSeptember_ThenTheCostIs199(){
        date = LocalDate.of(2020, 9, 13);
        RentalAgreement actual = service.createRentalAgreement("LADW", 1, 0, date);

        assertEquals(new BigDecimal("1.99"), actual.getFinalCharge());
    }

    @Test
    public void givenALadderRentalAgreement_WhenRentingOverThe4thOfJulyWithDiscount_ThenCostIs358Cents(){
        date = LocalDate.of(2020, 7, 2);
        RentalAgreement actual = service.createRentalAgreement("LADW", 3, 10, date);

        assertEquals(new BigDecimal("3.58"), actual.getFinalCharge());
    }

    @Test
    public void givenAChainsawRentalAgreement_WhenRentingOverTheWeekendWithDiscount_ThenCostIs335Cents(){
        date = LocalDate.of(2015, 7, 2);
        RentalAgreement actual = service.createRentalAgreement("CHNS", 5, 25, date);

        assertEquals(new BigDecimal("3.35"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRentalAgreement_WhenRentingOverLaborDay_ThenCostIs897Cents(){
        date = LocalDate.of(2015, 9, 3);
        RentalAgreement actual = service.createRentalAgreement("JAKD", 6, 0, date);

        assertEquals(new BigDecimal("8.97"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRentalAgreement_WhenRentingOverThe4thOfJuly_ThenCostIs1495Cents(){
        date = LocalDate.of(2015, 7, 2);
        RentalAgreement actual = service.createRentalAgreement("JAKR", 9, 0, date);

        assertEquals(new BigDecimal("14.95"), actual.getFinalCharge());
    }

    @Test
    public void givenAJackhammerRentalAgreement_WhenRentingOverThe4thOfJulyWithDiscount_ThenCostIs149Cents(){
        date = LocalDate.of(2020, 7, 2);
        RentalAgreement actual = service.createRentalAgreement("JAKR", 4, 50, date);

        assertEquals(new BigDecimal("1.49"), actual.getFinalCharge());
    }
}
