package mk.ukim.finki.emt.couponcatalog.services.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Data
public class CouponForm {

    private String couponName;
    private String company;
    private Money price;
    private int sales;
}
