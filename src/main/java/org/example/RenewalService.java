package org.example;

import java.util.Locale;

public class RenewalService {
    private final PriceList priceList;
    private final EmailSender emailSender;

    public RenewalService(PriceList priceList, EmailSender emailSender) {
        this.priceList = priceList;
        this.emailSender = emailSender;
    }

    public double renew(String plan, int months, String email) {
        double monthlyRate = priceList.getMonthlyPrice(plan);
        Subscription subscription = new Subscription(monthlyRate);
        double total = subscription.price(months);

        String receipt = String.format(Locale.US,
                "Підписку %s продовжено на %d міс. До сплати: %.2f грн",
                plan, months, total);
         emailSender.sendReceipt(email, receipt);

        return total;
    }
}