package org.nimi.casestudy.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * This class represents the Credit Card entity and Card Number will be validated
 * based on the type
 */
@Getter
@ToString
@Builder
public class CreditCard {

    @NotBlank
    @Pattern(regexp = "^[0-9]{13,16}$", message = "'cardNumber' must be of 13 to 16 with digits")
    private String cardNumber;

    @NotNull(message = "'cardType' should not be empty")
    private CreditCardType cardType;

    @NotBlank
    @Pattern(regexp = "\\d{6}", message = "'account number' should contain with 6 digits")
    private String accountNumber;

    @NotBlank(message = "'ownerId' should not be blank")
    private String ownerId;

    @PositiveOrZero(message = "'dueAmount' should a greater that zero")
    private double dueAmount;

    @Positive(message = "'creditLimit' should a greater that zero")
    private double creditLimit;

    /**
     * This method validate credit card number based on the type
     */
    public boolean validate() {
        if (Objects.isNull(this.cardType))
            throw new IllegalArgumentException("Card type should be VISA, MASTER, AMEX or DISCOVER");
        switch (this.cardType) {
            case VISA:
                return this.cardNumber.startsWith("4");
            case MASTER:
                return this.cardNumber.startsWith("5");
            case AMERICAN_EXPRESS:
                return this.cardNumber.startsWith("37");
            case DISCOVER:
                return this.cardNumber.startsWith("6");
            default:
                throw new IllegalArgumentException("Card type should be VISA, MASTER, AMEX or DISCOVER");
        }
    }

}
