package com.devdouglas.dsmeta.controller;

import com.devdouglas.dsmeta.entity.Sale;
import com.devdouglas.dsmeta.service.SaleService;
import com.devdouglas.dsmeta.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<Sale> getAllSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                  @RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable pageable) {
        return this.service.getAll(minDate, maxDate, pageable);
    }

    @GetMapping("/{saleId}/notification")
    public void notifySms(@PathVariable Long saleId) {
        smsService.sendSms(saleId);
    }
}
