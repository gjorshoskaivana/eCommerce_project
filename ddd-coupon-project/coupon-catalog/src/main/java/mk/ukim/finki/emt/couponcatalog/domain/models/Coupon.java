package mk.ukim.finki.emt.couponcatalog.domain.models;

import lombok.Getter;
import mk.ukim.finki.emt.couponcatalog.domain.valueObjects.Quantity;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;

@Entity
@Table(name="coupon")
@Getter
public class Coupon extends AbstractEntity<CouponId> {

    private String couponName;
    private String company;

    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "price_currency"))
    })
    private Money price;

    private Coupon(){
        super(CouponId.randomId(CouponId.class));
    }

    public static Coupon build(String couponName, String company, Money price, int sales){
        Coupon coupon = new Coupon();
        coupon.couponName = couponName;
        coupon.company = company;
        coupon.price = price;
        coupon.sales = sales;
        return coupon;
    }

    public void addSales(int quantity){
        this.sales = this.sales + quantity;
    }

    public void removeSales(int quantity){
        this.sales = this.sales - quantity;
    }
}
