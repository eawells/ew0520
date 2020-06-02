package com.toolrental.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RentalAgreement {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    private LocalDate checkoutDateDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private int chargeableDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public void printReceipt(){
        StringBuilder receipt = new StringBuilder();
        receipt.append("Tool code: " + toolCode +
                "\nTool type: " + toolType +
                "\nTool brand: " + toolBrand +
                "\nRental days: 1" +
                "\nCheck out date: 05/28/20" +
                "\nDue date: 05/29/20" +
                "\nDaily rental charge: $" + dailyCharge.toString() +
                "\nCharge days: 1" +
                "\nPre-discount charge: $" + preDiscountCharge.toString() +
                "\nDiscount percent: 0%" +
                "\nDiscount amount: $0.00" +
                "\nFinal charge: $" + finalCharge.toString());
        System.out.println(receipt);
    }
}
