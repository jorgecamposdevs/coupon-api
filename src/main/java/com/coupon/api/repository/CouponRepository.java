package com.coupon.api.repository;

import com.coupon.api.entities.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
}
