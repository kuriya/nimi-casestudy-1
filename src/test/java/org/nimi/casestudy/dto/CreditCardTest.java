package org.nimi.casestudy.dto;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class is responsible to test all credit card class business logic
 */
class CreditCardTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    /**
     * When Visa credit card number is valid
     * Then no errors
     */
    @Test
    void testVisaCardNumberValidNoErrors() {
       final CreditCard card = CreditCard.builder()
               .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
               .ownerId("12345").dueAmount(15000).creditLimit(250000)
               .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertTrue(card.validate());
    }

    /**
     * When Visa credit card number is not valid
     * Then validate method return false
     */
    @Test
    void testVisaCardNumberNotValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("32345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertFalse(card.validate());
    }

    /**
     * When Master credit card number is valid
     * Then no errors
     */
    @Test
    void testMasterCardNumberValidNoErrors() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("52345678912345").cardType(CreditCardType.MASTER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertTrue(card.validate());
    }

    /**
     * When Master credit card number is not valid
     * Then validate method return false
     */
    @Test
    void testMasterCardNumberNotValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("62345678912345").cardType(CreditCardType.MASTER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertFalse(card.validate());
    }

    /**
     * When American credit card number is valid
     * Then no errors
     */
    @Test
    void testAmericanExpressCardNumberValidNoErrors() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("37345678912345").cardType(CreditCardType.AMERICAN_EXPRESS).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertTrue(card.validate());
    }

    /**
     * When American credit card number is not valid
     * Then validate method return false
     */
    @Test
    void testAmericanExpressCardNumberNotValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("38345678912345").cardType(CreditCardType.AMERICAN_EXPRESS).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertFalse(card.validate());
    }

    /**
     * When Discover credit card number is valid
     * Then no errors
     */
    @Test
    void testDiscoverCardNumberValidNoErrors() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("62345678912345").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertTrue(card.validate());
    }

    /**
     * When Discover credit card number is not valid
     * Then no errors
     */
    @Test
    void testDiscoverCardNumberNotValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("72345678912345").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
        Assertions.assertFalse(card.validate());
    }


    /**
     * When credit card type is invalid
     * throw exception
     */
    @Test()
    void testValidateIllegalCardType() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("62345678912345").cardType(null).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate(card);
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, card::validate,
                "Card type should be VISA, MASTER, AMEX or DISCOVER"
        );
        Assertions.assertEquals("Card type should be VISA, MASTER, AMEX or DISCOVER", exception.getMessage());
    }

    /**
     * When credit card no with characters and digits
     * Then errors
     */
    @Test
    void testCardNumberWithCharactersAndDigits() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("abc34567ghh").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When credit card no with incorrect length of digits
     * Then errors
     */
    @Test
    void testCardNumberWithIncorrectLengthOfDigits() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("623456").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());

    }

    /**
     * When credit card no exceed max digits
     * Then errors
     */
    @Test
    void testCardNumberExceedMaxDigits() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("62345662345612345").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());

    }

    /**
     * When credit card no null
     * Then errors
     */
    @Test
    void testCardNumberNull() {
        final CreditCard card = CreditCard.builder()
                .cardNumber(null).cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When credit card is Blank
     * Then errors
     */
    @Test
    void testCardNumberIsBlank() {
        final CreditCard card = CreditCard.builder()
                .cardNumber(" ").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(2, constraintViolations.size());
    }

    /**
     * When credit card is Empty
     * Then errors
     */
    @Test
    void testCardNumberIsEmpty() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("").cardType(CreditCardType.DISCOVER).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(2, constraintViolations.size());

    }


    /**
     * When credit limit is negative
     * Then errors
     */
    @Test
    void testCreditLimitIsNegative() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(-250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When credit limit is zero
     * Then errors
     */
    @Test
    void testCreditLimitIsZero() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(0)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When due amount is negative
     * Then errors
     */
    @Test
    void testDueAmountIsNegative() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(-15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When due amount is zero
     * Then errors
     */
    @Test
    void testDueAmountIsZero() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(0).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
    }

    /**
     * When credit limit is valid
     * Then no error
     */
    @Test
    void testCreditLimitIsValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
    }

    /**
     * When due amount is valid
     * Then no errors
     */
    @Test
    void testDueAmountIsValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
    }


    /**
     * When owner id is valid
     * Then no errors
     */
    @Test
    void testOwnerIdIsValid() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(0, constraintViolations.size());
    }

    /**
     * When owner id is valid
     * Then errors
     */
    @Test
    void testOwnerIdIsNull() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId(null).dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When owner id is valid
     * Then errors
     */
    @Test
    void testOwnerIdIsBlank() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId(" ").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When owner id is valid
     * Then errors
     */
    @Test
    void testOwnerIdIsEmpty() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("123456")
                .ownerId("").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When account no is null
     * Then errors
     */
    @Test
    void testAccountNumberIsNull() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber(null)
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(1, constraintViolations.size());
    }

    /**
     * When account number is empty
     * Then errors
     */
    @Test
    void testAccountNumberIsEmpty() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber("")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(2, constraintViolations.size());
    }

    /**
     * When account number is blank
     * Then errors
     */
    @Test
    void testAccountNumberIsBlank() {
        final CreditCard card = CreditCard.builder()
                .cardNumber("42345678912345").cardType(CreditCardType.VISA).accountNumber(" ")
                .ownerId("12345").dueAmount(15000).creditLimit(250000)
                .build();
        final Set<ConstraintViolation<CreditCard>> constraintViolations = validator.validate( card );
        Assertions.assertEquals(2, constraintViolations.size());
    }


}