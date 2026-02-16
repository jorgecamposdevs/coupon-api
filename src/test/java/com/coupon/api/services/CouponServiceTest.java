package com.coupon.api.services;

import com.coupon.api.entities.CouponEntity;
import com.coupon.api.repository.CouponRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponService couponService;

    @Test
    void shouldCreateCouponSuccessfully() {

        CouponEntity coupon = new CouponEntity();
        coupon.setId(1L);

        when(couponRepository.save(coupon)).thenReturn(coupon);

        CouponEntity result = couponService.createCoupon(coupon);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(couponRepository, times(1)).save(coupon);
    }

    @Test
    void shouldSoftDeleteCoupon() {

        CouponEntity coupon = new CouponEntity();
        coupon.setId(1L);
        coupon.setDeleted(false);

        when(couponRepository.findById(1L))
                .thenReturn(Optional.of(coupon));

        couponService.softDeleteById(1L);

        assertTrue(coupon.getDeleted());
        verify(couponRepository).save(coupon);
    }

    @Test
    void shouldThrowExceptionWhenCouponNotFound() {

        when(couponRepository.findById(1L))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> couponService.softDeleteById(1L)
        );

        assertEquals("Cupom não encontrado", exception.getMessage());
        verify(couponRepository, never()).save(any());
    }
}