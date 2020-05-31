package com.toolrental.model;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = {"code"})
public class Tool {
    private String code;
    private ToolType type;
    private String brand;
}
