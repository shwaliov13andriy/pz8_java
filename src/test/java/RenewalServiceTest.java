package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RenewalServiceTest {

    @Mock
    private PriceList priceList;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private RenewalService renewalService;

    @Test
    void renewCalculatesDiscountedPriceAndSendsReceipt() {
        when(priceList.getMonthlyPrice("Premium")).thenReturn(199.0);

        double total = renewalService.renew("Premium", 6, "andriy@example.com");

        assertEquals(1074.6, total, 0.001);
        verify(emailSender).sendReceipt(
                "andriy@example.com",
                "Підписку Premium продовжено на 6 міс. До сплати: 1074.60 грн"
        );
    }
}