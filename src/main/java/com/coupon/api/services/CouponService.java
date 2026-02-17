package com.coupon.api.services;

import com.coupon.api.entities.CouponEntity;
import com.coupon.api.repository.CouponRepository;
import org.springframework.stereotype.Service;

@Service
public class CouponService  {

    private final CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public CouponEntity createCoupon(CouponEntity couponEntity) {
        return couponRepository.save(couponEntity);
    }

    public void softDeleteById(Long id) {

        CouponEntity coupon = couponRepository.findById(id)
                        .orElseThrow(()-> new RuntimeException("Cupom não encontrado"));

       coupon.setDeleted(Boolean.TRUE);

        couponRepository.save(coupon);
    }
}