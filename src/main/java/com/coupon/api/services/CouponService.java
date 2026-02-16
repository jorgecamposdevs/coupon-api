package com.coupon.api.services;

import com.coupon.api.entities.CouponEntity;
import com.coupon.api.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService  {

    private final CouponRepository couponRepository;

    public CouponEntity createCoupon(CouponEntity couponEntity) {
        return couponRepository.save(couponEntity);
    }
}