package com.coupon.api.mappers;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CouponMappers {

    private static final BigDecimal MIN_DISCOUNT = BigDecimal.valueOf(0.5);

    private static final boolean DEFAULT_PUBLISHED = false;

    public CouponEntity convertCouponRequestDTOToCouponEntity(CouponRequestDTO couponRequestDTO) {
        CouponEntity couponEntity = new CouponEntity();

        String normalizedCode = normalize(couponRequestDTO.getCode());
        couponEntity.setCode(normalizedCode);

        couponEntity.setDescription(couponRequestDTO.getDescription());

        BigDecimal discountValue = couponRequestDTO.getDiscountValue();

        if(discountValue == null) {
            throw new IllegalArgumentException("O valor de desconto é obrigatório");
        }

        if (discountValue.compareTo(MIN_DISCOUNT) < 0) {
            throw new IllegalArgumentException(
                    "O valor de desconto não pode ser menor que 0.5"
            );
        }

        LocalDate expirationDate = couponRequestDTO.getExpirationDate();

        if (expirationDate == null) {
            throw new IllegalArgumentException("A data de expiração é obrigatória");
        }

        if (expirationDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "O cupom não pode ser criado com data de expiração no passado"
            );
        }

        couponEntity.setExpirationDate(expirationDate);

        couponEntity.setDiscountValue(couponRequestDTO.getDiscountValue());
        couponEntity.setExpirationDate(couponRequestDTO.getExpirationDate());

        Boolean published = couponRequestDTO.getPublished();
        couponEntity.setPublished(published != null ? published : DEFAULT_PUBLISHED);

        return couponEntity;
    }

    private static String normalize(String rawCode) {

        if (rawCode == null) {
            throw new IllegalArgumentException("Código do cupom não pode ser nulo");
        }

        String normalized = rawCode.replaceAll("[^a-zA-Z0-9]", "");

        normalized = normalized.toUpperCase();

        if (normalized.length() != 6) {
            throw new IllegalArgumentException(
                    "Código do cupom deve conter exatamente 6 caracteres alfanuméricos"
            );
        }

        return normalized;
    }
 }