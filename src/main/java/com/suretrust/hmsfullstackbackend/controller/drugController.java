package com.suretrust.hmsfullstackbackend.controller;

import com.suretrust.hmsfullstackbackend.exception.DrugNotFoundException;
import com.suretrust.hmsfullstackbackend.model.Stock;
import com.suretrust.hmsfullstackbackend.repository.DrugStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class drugController {
    @Autowired
    private DrugStockRepository drugStockRepository;

    @PostMapping("/stock")
    Stock newStock(@RequestBody Stock newStock){
        return drugStockRepository.save(newStock);
    }

    @GetMapping("/stocks")
    List<Stock> getallDrugs(){
        return drugStockRepository.findAll();
    }

    @GetMapping("/stock/{id}")
    Stock getStockById(@PathVariable Long id){
        return drugStockRepository.findById(id)
                .orElseThrow(() -> new DrugNotFoundException(id));
    }


    @PutMapping("/stock/{medicineName}")
    Stock updateDrug(@RequestBody Stock newDrug, @PathVariable String medicineName) {
        Stock existingDrug = drugStockRepository.findByMedicineNameIgnoreCase(medicineName);

        if (existingDrug == null) {
            throw new DrugNotFoundException("No drug found with the given name: " + medicineName);
        }

        if (newDrug.getQuantity() != null) {
            existingDrug.setQuantity(newDrug.getQuantity());
        }

        if (newDrug.getPrice() != null) {
            existingDrug.setPrice(newDrug.getPrice());
        }

        return drugStockRepository.save(existingDrug);
    }



    // Delete Medicine
    @DeleteMapping("/stock/{id}")
    String deleteUser(@PathVariable Long id){
        if(!drugStockRepository.existsById(id)){
            throw new DrugNotFoundException(id);
        }
        drugStockRepository.deleteById(id);
        return "Drug with id " +id+ " has been deleted ";
    }

    // Search a medicine
    @GetMapping("/search/{medicineName}")
    public List<Stock> searchDrugsByName(@PathVariable String medicineName) {
        List<Stock> drugs = drugStockRepository.findByMedicineNameContainingIgnoreCase(medicineName);
        if (drugs.isEmpty()) {
            throw new DrugNotFoundException("No drugs found with the given name: " + medicineName);
        }

        return drugs;
    }

}

