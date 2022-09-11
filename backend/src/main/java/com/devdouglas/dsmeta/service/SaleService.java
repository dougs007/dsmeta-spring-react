package com.devdouglas.dsmeta.service;


import com.devdouglas.dsmeta.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SaleService {

    Page<Sale> getAll(String minDate, String maxDate, Pageable pageable);

}
