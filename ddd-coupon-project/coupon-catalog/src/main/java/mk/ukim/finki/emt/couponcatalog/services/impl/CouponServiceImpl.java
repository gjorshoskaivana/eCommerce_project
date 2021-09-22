package mk.ukim.finki.emt.couponcatalog.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.couponcatalog.domain.exceptions.CouponNotFoundException;
import mk.ukim.finki.emt.couponcatalog.domain.models.Coupon;
import mk.ukim.finki.emt.couponcatalog.domain.models.CouponId;
import mk.ukim.finki.emt.couponcatalog.domain.repository.CouponRepository;
import mk.ukim.finki.emt.couponcatalog.services.CouponService;
import mk.ukim.finki.emt.couponcatalog.services.forms.CouponForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final CouponRepository couponRepository;

    @Override
    public Coupon findByID(CouponId id) {
        return couponRepository.findById(id).orElseThrow(CouponNotFoundException::new);
    }

    @Override
    public Coupon createCoupon(CouponForm couponForm) {
        Coupon coupon  = Coupon.build(couponForm.getCouponName(), couponForm.getCompany(),
                couponForm.getPrice(), couponForm.getSales());
        couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public Coupon orderItemCreated(CouponId couponId, int quantity) {
        Coupon coupon = this.findByID(couponId);
        coupon.addSales(quantity);
        couponRepository.saveAndFlush(coupon);
        return coupon;
    }

    @Override
    public Coupon orderItemRemoved(CouponId couponId, int quantity) {
        Coupon coupon = this.findByID(couponId);
        coupon.removeSales(quantity);
        couponRepository.saveAndFlush(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> getAll() {
        return couponRepository.findAll();
    }
}
