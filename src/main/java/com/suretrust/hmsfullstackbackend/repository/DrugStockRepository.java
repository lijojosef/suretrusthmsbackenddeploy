package com.suretrust.hmsfullstackbackend.repository;

import com.suretrust.hmsfullstackbackend.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DrugStockRepository extends JpaRepository<Stock,Long> {
    @Query("SELECT s FROM Stock s WHERE LOWER(s.medicineName) LIKE %:medicineName%")
    List<Stock> findByMedicineNameContainingIgnoreCase(String medicineName);

    Stock findByMedicineNameIgnoreCase(String medicineName);

    Stock findByMedicineName(String medicineName);
}
