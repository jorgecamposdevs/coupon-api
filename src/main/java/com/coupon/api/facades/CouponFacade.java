package com.coupon.api.facades;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import com.coupon.api.mappers.CouponMappers;
import com.coupon.api.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponFacade {

    private final CouponService couponService;

    private final CouponMappers couponMappers;

    public CouponEntity createCoupon(CouponRequestDTO couponRequestDTO) {
        return couponService.createCoupon(couponMappers.convertCouponRequestDTOToCouponEntity(couponRequestDTO));
    }

    public void softDeleteById(Long id) {
        couponService.softDeleteById(id);
    }
}