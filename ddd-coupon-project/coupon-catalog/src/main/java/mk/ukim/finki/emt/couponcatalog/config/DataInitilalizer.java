package mk.ukim.finki.emt.couponcatalog.config;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.couponcatalog.domain.models.Coupon;
import mk.ukim.finki.emt.couponcatalog.domain.repository.CouponRepository;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitilalizer {

    private final CouponRepository couponRepository;

    @PostConstruct
    public void initData() {
        Coupon c1 = Coupon.build("Pizza", "Giacomo", Money.valueOf(Currency.MKD, 1000), 10);
        Coupon c2 = Coupon.build("Book", "Prosvetno delo", Money.valueOf(Currency.MKD, 300), 3);
        if (couponRepository.findAll().isEmpty()) {
            couponRepository.saveAll(Arrays.asList(c1, c2));
        }
    }
}
