package com.toolrental.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class RentalAgreementTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void givenALadderRentalAgreement_WhenPrintReceiptCalled_ThenAllFieldsArePrinted(){
        LocalDate date = LocalDate.of(2020, 05, 28);

        RentalAgreement agreement = new RentalAgreement("LADW", "Ladder", "Werner", 1, date, date.plusDays(1),
                                        new BigDecimal("1.99"), 1, new BigDecimal("1.99"), 0,
                                        new BigDecimal("0"), new BigDecimal("1.99"));
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
        agreement.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenAChainsawRentalAgreement_WhenPrintReceiptCalled_ThenAllFieldsArePrinted(){
        LocalDate date = LocalDate.of(2020, 05, 28);

        RentalAgreement agreement = new RentalAgreement("CHNS", "Chainsaw", "Stihl", 1, date, date.plusDays(1),
                                        new BigDecimal("1.49"), 1, new BigDecimal("1.49"), 0,
                                        new BigDecimal("0"), new BigDecimal("1.49"));
        String expectedReceipt =
                "Tool code: CHNS\n" +
                "Tool type: Chainsaw\n" +
                "Tool brand: Stihl\n" +
                "Rental days: 1\n" +
                "Check out date: 05/28/20\n" +
                "Due date: 05/29/20\n" +
                "Daily rental charge: $1.49\n" +
                "Charge days: 1\n" +
                "Pre-discount charge: $1.49\n" +
                "Discount percent: 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $1.49";
        agreement.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenATwoDayRentalAgreement_WhenPrintReceiptCalled_ThenAllFieldsArePrinted(){
        LocalDate rentalDate = LocalDate.of(2020, 05, 26);
        LocalDate dueDate = LocalDate.of(2020, 05, 28);

        RentalAgreement agreement = new RentalAgreement("CHNS", "Chainsaw", "Stihl", 2, rentalDate, dueDate,
                                        new BigDecimal("1.49"), 2, new BigDecimal("2.98"), 0,
                                        new BigDecimal("0"), new BigDecimal("2.98"));
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
        agreement.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }

    @Test
    public void givenADiscountedRentalAgreement_WhenPrintReceiptCalled_ThenAllFieldsArePrinted(){
        LocalDate rentalDate = LocalDate.of(2020, 05, 26);
        LocalDate dueDate = LocalDate.of(2020, 05, 28);

        RentalAgreement agreement = new RentalAgreement("JAKR", "Jackhammer", "Ridgid", 2, rentalDate, dueDate,
                                        new BigDecimal("2.99"), 2, new BigDecimal("5.98"), 10,
                                        new BigDecimal("0.60"), new BigDecimal("5.38"));
        String expectedReceipt =
                "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 2\n" +
                "Check out date: 05/26/20\n" +
                "Due date: 05/28/20\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 2\n" +
                "Pre-discount charge: $5.98\n" +
                "Discount percent: 10%\n" +
                "Discount amount: $0.60\n" +
                "Final charge: $5.38";
        agreement.printReceipt();
        assertEquals(expectedReceipt, outContent.toString().trim());
    }
}
