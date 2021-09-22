package mk.ukim.finki.emt.couponcatalog.domain.repository;

import mk.ukim.finki.emt.couponcatalog.domain.models.Coupon;
import mk.ukim.finki.emt.couponcatalog.domain.models.CouponId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, CouponId> {
}
