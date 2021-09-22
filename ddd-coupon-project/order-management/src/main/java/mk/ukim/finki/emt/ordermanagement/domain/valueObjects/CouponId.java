package mk.ukim.finki.emt.ordermanagement.domain.valueObjects;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

import javax.persistence.Embeddable;

@Embeddable
public class CouponId extends DomainObjectId {

    public CouponId() {
        super(CouponId.randomId(CouponId.class).getId());
    }

    public CouponId(String uuid) {
        super(uuid);
    }

}
