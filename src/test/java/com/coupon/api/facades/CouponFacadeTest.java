package com.coupon.api.facades;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import com.coupon.api.mappers.CouponMappers;
import com.coupon.api.services.CouponService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponFacadeTest {

    @Mock
    private CouponService couponService;

    @Mock
    private CouponMappers couponMappers;

    @InjectMocks
    private CouponFacade couponFacade;

    @Test
    void shouldCreateCouponSuccessfully() {

        CouponRequestDTO requestDTO = new CouponRequestDTO();
        requestDTO.setCode("AB-12@34");

        CouponEntity mappedEntity = new CouponEntity();
        mappedEntity.setId(1L);

        CouponEntity savedEntity = new CouponEntity();
        savedEntity.setId(1L);

        Mockito.when(couponMappers.convertCouponRequestDTOToCouponEntity(requestDTO)).thenReturn(mappedEntity);

        CouponEntity result = couponFacade.createCoupon(requestDTO);


        Assertions.assertNull(result);
        verify(couponMappers, times(1))
                .convertCouponRequestDTOToCouponEntity(requestDTO);
    }

    @Test
    void shouldCallServiceSoftDeleteById() {

        Long couponId = 1L;

        doNothing().when(couponService).softDeleteById(couponId);

        couponFacade.softDeleteById(couponId);

        verify(couponService, times(1)).softDeleteById(couponId);
    }
}