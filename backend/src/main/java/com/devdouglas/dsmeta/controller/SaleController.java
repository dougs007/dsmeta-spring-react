package com.devdouglas.dsmeta.controller;

import com.devdouglas.dsmeta.dto.SaleDTO;
import com.devdouglas.dsmeta.entity.Sale;
import com.devdouglas.dsmeta.service.SaleService;
import com.devdouglas.dsmeta.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService service;
    private final SmsService smsService;

    @Autowired
    public SaleController(SaleService service, SmsService smsService) {
        this.service = service;
        this.smsService = smsService;
    }

    @Operation(summary = "Get All Sales")
    @GetMapping
    public Page<Sale> getAllSales(@Parameter(description = "minimum date for search")
                                  @RequestParam(value = "minDate", defaultValue = "") String minDate,
                                  @Parameter(description = "max date for search") @RequestParam(value = "maxDate", defaultValue = "") String maxDate,
                                  Pageable pageable) {
        return this.service.getAll(minDate, maxDate, pageable);
    }

    @Operation(summary = "Get Sale By Id")
    @GetMapping("/{saleId}")
    public SaleDTO findSaleById(@Parameter(description = "id of sale to be detailed") @PathVariable Long saleId) {
        return this.service.getSaleById(saleId);
    }

    @Operation(summary = "Create a new Sale By Id")
    @PostMapping
    public SaleDTO createSale(@RequestBody SaleDTO saleDTO) {
        return this.service.create(saleDTO);
    }

    @Operation(summary = "Update an Sale By Id")
    @PutMapping("/{saleId}")
    public SaleDTO update(@Parameter(description = "id of sale to be updated") @PathVariable Long saleId,
                          @RequestBody SaleDTO saleDTO) {
        saleDTO.setId(saleId);
        return this.service.update(saleDTO);
    }

    @Operation(summary = "id of sale to be deleted")
    @DeleteMapping("/{saleId}")
    public void update(@Parameter(description = "id of sale to be deleted") @PathVariable Long saleId) {
        this.service.delete(saleId);
    }

    @Operation(summary = "id of sale to be notified")
    @GetMapping("/{saleId}/notification")
    public void notifySms(@Parameter(description = "id of sale to be notified") @PathVariable Long saleId) {
        this.smsService.sendSms(saleId);
    }
}
