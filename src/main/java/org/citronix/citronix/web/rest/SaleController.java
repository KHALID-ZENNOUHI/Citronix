package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.citronix.citronix.domain.Sale;
import org.citronix.citronix.service.SaleService;
import org.citronix.citronix.web.vm.SaleVM;
import org.citronix.citronix.web.vm.mapper.SaleMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    private final SaleMapper saleMapper;

    @PostMapping("/save")
    public ResponseEntity<SaleVM> save(@Valid @RequestBody SaleVM saleVM) {
        Sale sale = saleService.save(saleMapper.toSale(saleVM));
        SaleVM saleVMResponse = saleMapper.toSaleVM(sale);
        saleVMResponse.setIncome(sale.getIncome());
        return ResponseEntity.status(201).body(saleVMResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<SaleVM> update(@Valid @RequestBody SaleVM saleVM) {
        Sale sale = saleService.update(saleMapper.toSale(saleVM));
        SaleVM saleVMResponse = saleMapper.toSaleVM(sale);
        saleVMResponse.setIncome(sale.getIncome());
        return ResponseEntity.ok(saleVMResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SaleVM> details(@PathVariable Long id) {
        Sale sale = saleService.findById(id);
        SaleVM saleVM = saleMapper.toSaleVM(sale);
        saleVM.setIncome(sale.getIncome());
        return ResponseEntity.ok(saleVM);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SaleVM>> findAll(@RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page, @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size) {
        Page<Sale> sales = saleService.findAll(page, size);
        Page<SaleVM> saleVMS = sales.map(saleMapper::toSaleVM);
        return ResponseEntity.ok(saleVMS);
    }
}
