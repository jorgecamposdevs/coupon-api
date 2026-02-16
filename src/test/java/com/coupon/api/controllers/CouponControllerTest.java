package com.coupon.api.controllers;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import com.coupon.api.facades.CouponFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponControllerTest {

    @InjectMocks
    private CouponController couponController;

    @Mock
    private CouponFacade couponFacade;


    @Test
    void shouldCreateCouponAndReturn201() {

        CouponRequestDTO requestDTO = new CouponRequestDTO();
        requestDTO.setCode("AB-12@34");
        requestDTO.setDescription("Cupom teste");
        requestDTO.setDiscountValue(BigDecimal.valueOf(10.00));
        requestDTO.setExpirationDate(LocalDate.now().plusDays(10));

        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(1L);

        when(couponFacade.createCoupon(any(CouponRequestDTO.class)))
                .thenReturn(couponEntity);

        ResponseEntity<CouponEntity> response =
                couponController.createCoupon(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(couponEntity, response.getBody());

        verify(couponFacade, times(1)).createCoupon(requestDTO);
    }

    @Test
    void shouldSoftDeleteCouponAndReturn204() {

        Long couponId = 1L;

        doNothing().when(couponFacade).softDeleteById(couponId);

        ResponseEntity<Void> response =
                couponController.deleteByIdCoupon(couponId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(couponFacade, times(1)).softDeleteById(couponId);
    }
}