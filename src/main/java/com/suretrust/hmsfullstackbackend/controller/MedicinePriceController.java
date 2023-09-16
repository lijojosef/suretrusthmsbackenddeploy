
package com.suretrust.hmsfullstackbackend.controller;

import com.suretrust.hmsfullstackbackend.exception.DrugNotFoundException;
import com.suretrust.hmsfullstackbackend.model.Stock;
import com.suretrust.hmsfullstackbackend.repository.DrugStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicine-price")
@CrossOrigin("http://localhost:3000") // Allow requests from your frontend
public class MedicinePriceController {

    @Autowired
    private DrugStockRepository drugStockRepository;

    @GetMapping("/{medicineName}")
    public double getMedicinePrice(@PathVariable String medicineName) {
        // Retrieve the medicine price from the database based on the medicine name
        Stock medicine = drugStockRepository.findByMedicineName(medicineName);

        if (medicine != null) {
            return medicine.getPrice();
        } else {
            throw new DrugNotFoundException("Medicine not found: " + medicineName);
        }
    }
}
