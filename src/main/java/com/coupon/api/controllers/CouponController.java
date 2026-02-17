package com.coupon.api.controllers;

import com.coupon.api.dtos.requests.CouponRequestDTO;
import com.coupon.api.entities.CouponEntity;
import com.coupon.api.facades.CouponFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/coupons")
@RequiredArgsConstructor
@Tag(name = "Coupons", description = "Operações relacionadas a cupons")
public class CouponController {

    private final CouponFacade couponFacade;


    @Operation(
            summary = "Criar cupom",
            description = "Cria um novo cupom com regras de negócio aplicadas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cupom criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno")
    })
    @PostMapping
    public ResponseEntity<CouponEntity> createCoupon(@RequestBody CouponRequestDTO couponRequestDTO) {
        return new ResponseEntity<>(couponFacade.createCoupon(couponRequestDTO), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Remover cupom (soft delete)",
            description = "Remove logicamente um cupom pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cupom removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cupom não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdCoupon(@PathVariable Long id) {
        couponFacade.softDeleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}