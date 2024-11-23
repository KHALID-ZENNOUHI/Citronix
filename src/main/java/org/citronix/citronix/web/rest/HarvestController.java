package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.citronix.citronix.domain.Harvest;
import org.citronix.citronix.service.HarvestService;
import org.citronix.citronix.web.vm.HarvestVM;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/harvest")
@RequiredArgsConstructor
public class HarvestController {
    private final HarvestService harvestService;

    @PostMapping("/save")
    public ResponseEntity<Harvest> save(@Valid @RequestBody HarvestVM harvestVM) {
        Harvest harvest = harvestService.save(harvestVM.getHarvestDate(), harvestVM.getFieldId());
        return ResponseEntity.status(201).body(harvest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Harvest> findById(@PathVariable Long id) {
        Harvest harvest = harvestService.findById(id);
        return ResponseEntity.ok(harvest);
    }

    @PutMapping("/update")
    public ResponseEntity<Harvest> update(@Valid @RequestBody HarvestVM harvestVM) {
        Harvest harvest = harvestService.update(harvestVM.getId(), harvestVM.getHarvestDate(), harvestVM.getFieldId());
        return ResponseEntity.ok(harvest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        harvestService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Harvest>> findAll(@RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page, @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size) {
        Page<Harvest> harvests = harvestService.findAll(page, size);
        return ResponseEntity.ok(harvests);
    }
}
