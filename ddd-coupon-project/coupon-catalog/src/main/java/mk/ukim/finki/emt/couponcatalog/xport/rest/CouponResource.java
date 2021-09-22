package mk.ukim.finki.emt.couponcatalog.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.couponcatalog.domain.models.Coupon;
import mk.ukim.finki.emt.couponcatalog.services.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@AllArgsConstructor
public class CouponResource {

    private final CouponService couponService;

    @GetMapping
    public List<Coupon> getAll(){
        return couponService.getAll();
    }
}
