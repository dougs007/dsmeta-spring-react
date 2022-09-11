package com.devdouglas.dsmeta.repository;

import com.devdouglas.dsmeta.entity.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(" FROM Sale a WHERE a.date BETWEEN :min AND :max ORDER BY a.amount DESC ")
    Page<Sale> findSales(LocalDate min, LocalDate max, Pageable pageable);
}
