package com.coupon.api.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequestDTO {

    private String code;

    private String description;

    private BigDecimal discountValue;

    private LocalDate expirationDate;

    private Boolean published;
}