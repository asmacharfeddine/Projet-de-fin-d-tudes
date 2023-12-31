package iss4u.ehr.backoffice.parameterization.prescription.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum period {
    ONE(1),
    TWO(2),
    THREE(3);

    private final int value;

    // You can also add methods to the enum, if needed.
    // For example, you can create a method to get the numerical value.
    public int getValue() {
        return value;
    }
}





