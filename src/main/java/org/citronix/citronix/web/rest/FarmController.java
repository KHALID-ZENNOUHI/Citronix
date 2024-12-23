package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.web.vm.FarmVM;
import org.citronix.citronix.web.vm.mapper.FarmMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Farm savedFarm = farmService.save(farm);
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

    @GetMapping("/search")
    public ResponseEntity<List<FarmVM>> search(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String location,
                                         @RequestParam(required = false) Double area) {
        List<Farm> farms = farmService.search(name, location, area);
        List<FarmVM> farmVMS = farms.stream().map(farmMapper::toFarmVM).toList();
        return ResponseEntity.ok(farmVMS);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<FarmVM>> findAll(@RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page, @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size) {
        Page<Farm> farms = farmService.findAll(page, size);
        Page<FarmVM> farmVMs = farms.map(farmMapper::toFarmVM);
        return ResponseEntity.ok(farmVMs);
    }


}
