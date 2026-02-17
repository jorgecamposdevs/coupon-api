package com.coupon.api.services;

import com.coupon.api.entities.CouponEntity;
import com.coupon.api.repository.CouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @DisplayName("Deve criar um cupom")
    void shouldCreateCouponSuccessfully() {

        CouponEntity input = new CouponEntity();
        input.setCode("SAVE20");
        input.setDiscountValue(new BigDecimal("20.00"));
        input.setExpirationDate(LocalDate.now().plusDays(10));

        CouponEntity output = new CouponEntity();
        output.setId(1L);
        output.setCode("SAVE20");
        output.setDiscountValue(new BigDecimal("20.00"));
        output.setPublished(true);

        when(couponRepository.save(input)).thenReturn(output);

        CouponEntity result = couponService.createCoupon(input);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("SAVE20", result.getCode());
        assertEquals(new BigDecimal("20.00"), result.getDiscountValue());

        verify(couponRepository, times(1)).save(input);
    }

    @Test
    @DisplayName("Deve marcar o cupom como deletado quando o ID existir")
    void shouldSoftDeleteCouponSuccessfully() {

        Long couponId = 1L;

        CouponEntity coupon = new CouponEntity();
        coupon.setId(couponId);
        coupon.setDeleted(Boolean.FALSE);

        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));

        couponService.softDeleteById(couponId);

        assertTrue(coupon.isDeleted());
        verify(couponRepository, times(1)).save(coupon);

    }


    @Test
    @DisplayName("Deve lançar uma exceção")
    void shouldThrowExceptionWhenCouponNotFound() {

        Long couponId = 1L;

        when(couponRepository.findById(couponId))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> couponService.softDeleteById(couponId)
        );

        assertEquals("Cupom não encontrado", exception.getMessage());
        verify(couponRepository, never()).save(any());
    }
}