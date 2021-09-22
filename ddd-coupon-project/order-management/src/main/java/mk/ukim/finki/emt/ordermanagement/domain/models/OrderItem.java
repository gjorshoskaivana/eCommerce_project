package mk.ukim.finki.emt.ordermanagement.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.CouponId;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    private Money itemPrice;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "coupon_id", nullable = false))
    private CouponId couponId;

    private OrderItem(){
        super(OrderItemId.randomId(OrderItemId.class));
    }

    public OrderItem(@NonNull CouponId couponId, @NonNull Money itemPrice, int quantity){
        super(OrderItemId.randomId(OrderItemId.class));
        this.couponId = couponId;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }

}
