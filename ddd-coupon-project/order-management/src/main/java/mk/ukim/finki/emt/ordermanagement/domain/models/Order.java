package mk.ukim.finki.emt.ordermanagement.domain.models;

import lombok.Getter;
import lombok.NonNull;
import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Coupon;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Currency;
import mk.ukim.finki.emt.sharedkernel.domain.financial.Money;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {

    /*
    * aggregate root for order-management
    * makes all the changes in the models that are part of the aggregate
    * */

    private Instant orderedOn;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @Column(name = "order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems;

    private Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency){
        super(OrderId.randomId(OrderId.class));
        this.orderedOn = now;
        this.currency = currency;
    }

    public Money total(){
        return orderItems.stream().map(OrderItem::subtotal).reduce(
                new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull Coupon coupon, int quantity){
        Objects.requireNonNull(coupon, "coupon must not be null");
        var item = new OrderItem(coupon.getId(), coupon.getPrice(), quantity);
        orderItems.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId){
        Objects.requireNonNull(orderItemId, "orderItemId must not be null");
        orderItems.removeIf(v -> v.getId().equals(orderItemId));
    }
}
