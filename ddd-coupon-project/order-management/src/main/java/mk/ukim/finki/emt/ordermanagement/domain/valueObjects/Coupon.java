package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

@Getter
public class Coupon implements ValueObject {
    private final CouponId id;
    private final String name;
    private final String company;
    private final Money price;
    private final int sales;

    private Coupon(){
        this.id = CouponId.randomId(CouponId.class);
        this.name = "";
        this.company = "";
        this.price = Money.valueOf(Currency.MKD, 0.0);
        this.sales = 0;
    }

    @JsonCreator
    public Coupon(@JsonProperty("id") CouponId id,
                  @JsonProperty("couponName") String name,
                  @JsonProperty("couponCompany")String company,
                  @JsonProperty("price")Money price,
                  @JsonProperty("sales") int sales) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.sales = sales;
    }
}
