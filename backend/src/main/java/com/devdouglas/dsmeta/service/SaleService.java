package com.devdouglas.dsmeta.service;


import com.devdouglas.dsmeta.dto.SaleDTO;
import com.devdouglas.dsmeta.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {

    Page<Sale> getAll(String minDate, String maxDate, Pageable pageable);

    SaleDTO getSaleById(Long saleId);

    SaleDTO update(SaleDTO saleDTO);

    SaleDTO create(SaleDTO saleDTO);

    void delete(Long saleId);
}
