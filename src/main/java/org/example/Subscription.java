package org.example;

public class Subscription {
    private static final double DEFAULT_MONTHLY_RATE = 199.0;
    private static final int DISCOUNT_MIN_MONTHS = 6;
    private static final double DISCOUNT_MULTIPLIER = 0.90;

    private final double monthlyRate;

    public Subscription() {
        this(DEFAULT_MONTHLY_RATE);
    }

    public Subscription(double monthlyRate) {
        if (monthlyRate <= 0) {
            throw new IllegalArgumentException("Місячний тариф має бути більшим за 0");
        }
        this.monthlyRate = monthlyRate;
    }

    public double price(int months) {
        if (months < 1 || months > 12) {
            throw new IllegalArgumentException("Кількість місяців має бути від 1 до 12, отримано: " + months);
        }
        double total = months * monthlyRate;
        if (months >= DISCOUNT_MIN_MONTHS) {
            total *= DISCOUNT_MULTIPLIER;
        }
        return total;
    }
}