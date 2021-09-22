package mk.ukim.finki.emt.couponcatalog.domain.models;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;
import org.springframework.lang.NonNull;

public class CouponId extends DomainObjectId {

    private CouponId(){
        super(CouponId.randomId(CouponId.class).getId());
    }

    public CouponId(@NonNull String uuid){
        super(uuid);
    }

    public static CouponId of(String uuid){
        CouponId couponId = new CouponId(uuid);
        return couponId;
    }
}
