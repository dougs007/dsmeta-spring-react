package com.devdouglas.dsmeta.service.impl;

import com.devdouglas.dsmeta.dto.SaleDTO;
import com.devdouglas.dsmeta.entity.Sale;
import com.devdouglas.dsmeta.exception.ResourceNotFoundException;
import com.devdouglas.dsmeta.repository.SaleRepository;
import com.devdouglas.dsmeta.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<Sale> getAll(String minDate, String maxDate, Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate min = maxDate.equals("") ? today.minusYears(1) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);

        return repository.findSales(min, max, pageable);
    }

    @Override
    public SaleDTO getSaleById(Long saleId) {
        Sale sale = this.findById(saleId);

        return mapper.map(sale, SaleDTO.class);
    }

    @Override
    public SaleDTO update(SaleDTO saleDTO) {
        Sale sale = this.findById(saleDTO.getId());

        sale.setSellerName(saleDTO.getSellerName());
        sale.setVisited(saleDTO.getVisited());
        sale.setDeals(saleDTO.getDeals());
        sale.setAmount(saleDTO.getAmount());
        sale.setDate(saleDTO.getDate());

        sale = repository.saveAndFlush(sale);

        return mapper.map(sale, SaleDTO.class);
    }

    @Override
    public SaleDTO create(SaleDTO saleDTO) {
        Sale sale = mapper.map(saleDTO, Sale.class);

        sale = this.repository.saveAndFlush(sale);

        return mapper.map(sale, SaleDTO.class);
    }

    @Override
    public void delete(Long saleId) {
        Sale sale = this.findById(saleId);
        this.repository.deleteById(sale.getId());
    }

    private Sale findById(Long saleId) {
        return this.repository.findById(saleId).orElseThrow(
                () -> new ResourceNotFoundException("Sale not found")
        );
    }
}
