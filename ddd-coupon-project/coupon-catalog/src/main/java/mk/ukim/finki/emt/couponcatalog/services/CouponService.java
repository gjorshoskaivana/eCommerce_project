package mk.ukim.finki.emt.couponcatalog.services;

import mk.ukim.finki.emt.couponcatalog.domain.models.Coupon;
import mk.ukim.finki.emt.couponcatalog.domain.models.CouponId;
import mk.ukim.finki.emt.couponcatalog.services.forms.CouponForm;

import java.util.List;

public interface CouponService {
    Coupon findByID(CouponId id);
    Coupon createCoupon(CouponForm couponForm);
    Coupon orderItemCreated(CouponId couponId, int quantity);
    Coupon orderItemRemoved(CouponId couponId, int quantity);
    List<Coupon> getAll();
}
