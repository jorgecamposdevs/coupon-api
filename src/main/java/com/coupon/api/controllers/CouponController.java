package com.coupon.api.controllers;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import com.coupon.api.facades.CouponFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponFacade couponFacade;

    @PostMapping
    public ResponseEntity<CouponEntity> createCoupon(@RequestBody CouponRequestDTO couponRequestDTO) {
        return new ResponseEntity<>(couponFacade.createCoupon(couponRequestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdCoupon(@PathVariable Long id) {
        couponFacade.softDeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}