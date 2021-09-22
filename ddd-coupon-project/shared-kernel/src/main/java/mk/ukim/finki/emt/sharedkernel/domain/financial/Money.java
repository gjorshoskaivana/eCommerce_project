package mk.ukim.finki.emt.sharedkernel.domain.financial;

import com.sun.istack.NotNull;
import lombok.Getter;
import mk.ukim.finki.emt.sharedkernel.domain.base.ValueObject;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
public class Money implements ValueObject {

    /*
    * Good practice is for all financial-related things to be put in one place
    * This business logic is in the shared kernel because both the coupon-catalog
    * module and the order-management module are using money-related objects
    * */

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;
    private final double amount;

    protected Money(){
        this.currency = null;
        this.amount = 0;
    }

    public Money(@NotNull Currency currency, @NotNull double amount){
        this.currency = currency;
        this.amount = amount;
    }

    public static Money valueOf(Currency currency, double amount){
        return new Money(currency, amount);
    }

    public Money add(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot add two Money objects with different currencies");
        }
        return new Money(currency, amount + money.amount);
    }

    public Money subtract(Money money){
        if(!currency.equals(money.currency)){
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies");
        }
        return new Money(currency, amount - money.amount);
    }

    public Money multiply(int m) {
        return new Money(currency, m * amount);
    }
}
