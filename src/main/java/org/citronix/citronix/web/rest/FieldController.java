package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.service.FieldService;
import org.citronix.citronix.web.vm.FieldResponseVM;
import org.citronix.citronix.web.vm.FieldVM;
import org.citronix.citronix.web.vm.mapper.FieldMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/field")
public class FieldController {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    public FieldController(FieldService fieldService, FieldMapper fieldMapper) {
        this.fieldService = fieldService;
        this.fieldMapper = fieldMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<FieldResponseVM> save(@Valid @RequestBody FieldVM fieldVM) {
        Field field = fieldMapper.toField(fieldVM);
        Field savedField = fieldService.save(field);
        FieldResponseVM savedFieldVM = fieldMapper.toFieldResponseVM(savedField);
        return ResponseEntity.status(201).body(savedFieldVM);
    }

    @PostMapping("/update")
    public ResponseEntity<FieldResponseVM> update(@Valid @RequestBody FieldVM fieldVM) {
        Field field = fieldMapper.toField(fieldVM);
        Field updatedField = fieldService.update(field);
        FieldResponseVM updatedFieldVM = fieldMapper.toFieldResponseVM(updatedField);
        return ResponseEntity.status(200).body(updatedFieldVM);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldResponseVM> details(@PathVariable Long id) {
        Field field = fieldService.findById(id);
        FieldResponseVM fieldVM = fieldMapper.toFieldResponseVM(field);
        return ResponseEntity.ok(fieldVM);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<FieldResponseVM>> findAll(@RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page, @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size) {
        Page<Field> fields = fieldService.findAll(page, size);
        Page<FieldResponseVM> fieldVMs = fields.map(fieldMapper::toFieldResponseVM);
        return ResponseEntity.ok(fieldVMs);
    }



}
