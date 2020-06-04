package com.toolrental.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

public class ToolRentalControllerTest {
    private ToolRentalController controller;
    private LocalDate date;

    @Before
    public void setUp(){
        controller = new ToolRentalController();
        date = LocalDate.of(2020, 05, 27);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void givenALadderRental_WhenDiscountIsGreaterThan100_ThenErrorIsThrown(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Please enter a discount percent between 0 and 100");

        controller.checkout("LADW", 1, 101, date);
    }

    @Test
    public void givenALadderRental_WhenDiscountIsLessThan0_ThenErrorIsThrown(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Please enter a discount percent between 0 and 100");

        controller.checkout("LADW", 1, -1, date);
    }

    @Test
    public void givenALadderRental_WhenRentalDayCountIs0_ThenErrorIsThrown(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Please enter at least one day for renting tool");

        controller.checkout("LADW", 0, 0, date);
    }

    @Test
    public void givenAJackhammerRental_WhenDiscountIsGreaterThan100_ThenErrorIsThrown(){
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Please enter a discount percent between 0 and 100");

        date = LocalDate.of(2015, 9, 3);

        controller.checkout("JAKR", 5, 101, date);
    }
}
