package com.devdouglas.dsmeta.service.impl;

import com.devdouglas.dsmeta.entity.Sale;
import com.devdouglas.dsmeta.repository.SaleRepository;
import com.devdouglas.dsmeta.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository repository;

    @Autowired
    public SaleServiceImpl(SaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Sale> getAll(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = maxDate.equals("") ? today.minusYears(1) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return repository.findSales(min, max, pageable);
    }
}
