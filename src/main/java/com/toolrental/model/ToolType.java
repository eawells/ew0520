package com.toolrental.model;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"type"})
public class ToolType {
    private String type;
    private BigDecimal dailyCost;
    private boolean weekdayChargeable;
    private boolean weekendChargeable;
    private boolean holidayChargeable;

}
