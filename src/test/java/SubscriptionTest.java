package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SubscriptionTest {


    @Test
    void priceWithoutDiscountForThreeMonths() {
        Subscription subscription = new Subscription();

        double result = subscription.price(3);

        assertEquals(597.0, result, 0.001);
    }

    @Test
    void priceWithDiscountForSixMonths() {
        Subscription subscription = new Subscription();

        double result = subscription.price(6);

        assertEquals(1074.6, result, 0.001);
    }

    @Test
    void priceThrowsExceptionForInvalidMonths() {

        Subscription subscription = new Subscription();

        assertThrows(IllegalArgumentException.class, () -> subscription.price(0));
    }


    @ParameterizedTest
    @CsvSource({
            "1, 199.0",
            "5, 995.0",
            "6, 1074.6",
            "12, 2149.2"
    })
    void priceOnValidBoundaries(int months, double expectedPrice) {
        Subscription subscription = new Subscription();

        double actualPrice = subscription.price(months);

        assertEquals(expectedPrice, actualPrice, 0.001);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 13})
    void priceOutOfRangeThrowsException(int invalidMonths) {
        Subscription subscription = new Subscription();

        assertThrows(IllegalArgumentException.class, () -> subscription.price(invalidMonths));
    }
}