package com.toolrental.integration;

import com.toolrental.controller.ToolRentalController;
import com.toolrental.model.RentalAgreement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private ToolRentalController controller;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        controller = new ToolRentalController();
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void givenARidgidJackhammerRentalAgreement_WhenRentingOverThe4thOfJuly_ThenReceiptPrintsAllFields() {
        LocalDate date = LocalDate.of(2015, 7, 2);

        String expectedReceipt =
                        "Tool code: JAKR\n" +
                        "Tool type: Jackhammer\n" +
                        "Tool brand: Ridgid\n" +
                        "Rental days: 9\n" +
                        "Check out date: 07/02/15\n" +
                        "Due date: 07/11/15\n" +
                        "Daily rental charge: $2.99\n" +
                        "Charge days: 5\n" +
                        "Pre-discount charge: $14.95\n" +
                        "Discount percent: 0%\n" +
                        "Discount amount: $0.00\n" +
                        "Final charge: $14.95";

        RentalAgreement actual = controller.checkout("JAKR", 9, 0, date);

        actual.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenADeWaltJackhammerRentalAgreement_WhenRentingOver2Weekdays_ThenReceiptPrintsAllFields() {
        LocalDate date = LocalDate.of(2020, 5, 26);

        String expectedReceipt =
                        "Tool code: JAKD\n" +
                        "Tool type: Jackhammer\n" +
                        "Tool brand: DeWalt\n" +
                        "Rental days: 2\n" +
                        "Check out date: 05/26/20\n" +
                        "Due date: 05/28/20\n" +
                        "Daily rental charge: $2.99\n" +
                        "Charge days: 2\n" +
                        "Pre-discount charge: $5.98\n" +
                        "Discount percent: 10%\n" +
                        "Discount amount: $0.60\n" +
                        "Final charge: $5.38";

        RentalAgreement actual = controller.checkout("JAKD", 2, 10, date);

        actual.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenAChainsawRentalAgreement_WhenRentingOver2Weekdays_ThenReceiptPrintsAllFields() {
        LocalDate date = LocalDate.of(2020, 5, 26);

        String expectedReceipt =
                        "Tool code: CHNS\n" +
                        "Tool type: Chainsaw\n" +
                        "Tool brand: Stihl\n" +
                        "Rental days: 2\n" +
                        "Check out date: 05/26/20\n" +
                        "Due date: 05/28/20\n" +
                        "Daily rental charge: $1.49\n" +
                        "Charge days: 2\n" +
                        "Pre-discount charge: $2.98\n" +
                        "Discount percent: 0%\n" +
                        "Discount amount: $0.00\n" +
                        "Final charge: $2.98";

        RentalAgreement actual = controller.checkout("CHNS", 2, 0, date);

        actual.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenALadderRentalAgreement_WhenRentingOver1Weekday_ThenReceiptPrintsAllFields() {
        LocalDate date = LocalDate.of(2020, 5, 28);

        String expectedReceipt =
                        "Tool code: LADW\n" +
                        "Tool type: Ladder\n" +
                        "Tool brand: Werner\n" +
                        "Rental days: 1\n" +
                        "Check out date: 05/28/20\n" +
                        "Due date: 05/29/20\n" +
                        "Daily rental charge: $1.99\n" +
                        "Charge days: 1\n" +
                        "Pre-discount charge: $1.99\n" +
                        "Discount percent: 0%\n" +
                        "Discount amount: $0.00\n" +
                        "Final charge: $1.99";

        RentalAgreement actual = controller.checkout("LADW", 1, 0, date);

        actual.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }
}
