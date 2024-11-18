package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.web.vm.FarmVM;
import org.citronix.citronix.web.vm.mapper.FarmMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farm")
public class FarmController {
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    public FarmController(FarmService farmService, FarmMapper farmMapper) {
        this.farmService = farmService;
        this.farmMapper = farmMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FarmVM> save(@Valid @RequestBody FarmVM farmVM) {
        Farm farm = farmMapper.toFarm(farmVM);
        Farm savedFarm = farmService.update(farm);
        FarmVM savedFarmVM = farmMapper.toFarmVM(savedFarm);
        return ResponseEntity.status(201).body(savedFarmVM);
    }

    @PutMapping("/update")
    public ResponseEntity<FarmVM> update(@Valid @RequestBody FarmVM farmVM) {
        Farm farm = farmMapper.toFarm(farmVM);
        Farm updatedFarm = farmService.update(farm);
        FarmVM updatedFarmVM = farmMapper.toFarmVM(updatedFarm);
        return ResponseEntity.ok(updatedFarmVM);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmVM> findById(@PathVariable Long id) {
        Farm farm = farmService.findById(id);
        FarmVM farmVM = farmMapper.toFarmVM(farm);
        return ResponseEntity.ok(farmVM);
    }
}
