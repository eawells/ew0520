package com.toolrental.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyCharge;
    private int chargeableDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public void printReceipt(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YY");
        StringBuilder receipt = new StringBuilder();
        receipt.append("Tool code: ");
        receipt.append(toolCode);

        receipt.append("\nTool type: ");
        receipt.append(toolType);

        receipt.append("\nTool brand: ");
        receipt.append(toolBrand);

        receipt.append("\nRental days: ");
        receipt.append(rentalDays);

        receipt.append("\nCheck out date: ");
        receipt.append(formatter.format(checkoutDate));

        receipt.append("\nDue date: ");
        receipt.append(formatter.format(dueDate));

        receipt.append("\nDaily rental charge: $");
        receipt.append(dailyCharge.setScale(2));

        receipt.append("\nCharge days: ");
        receipt.append(chargeableDays);

        receipt.append("\nPre-discount charge: $");
        receipt.append(preDiscountCharge.setScale(2));

        receipt.append("\nDiscount percent: ");
        receipt.append(discountPercent);
        receipt.append("%");

        receipt.append("\nDiscount amount: $");
        receipt.append(discountAmount.setScale(2));

        receipt.append("\nFinal charge: $");
        receipt.append(finalCharge.setScale(2));

        System.out.println(receipt);
    }
}
