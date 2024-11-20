package org.citronix.citronix.web.rest;

import jakarta.validation.Valid;
import org.citronix.citronix.domain.Tree;
import org.citronix.citronix.service.TreeService;
import org.citronix.citronix.web.vm.TreeVM;
import org.citronix.citronix.web.vm.mapper.TreeMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tree")
public class TreeController {

    private final TreeService treeService;
    private final TreeMapper treeMapper;

    public TreeController(TreeService treeService, TreeMapper treeMapper) {
        this.treeService = treeService;
        this.treeMapper = treeMapper;
    }


    @PostMapping("/save")
    public ResponseEntity<TreeVM> save(@Valid @RequestBody TreeVM treeVM) {
        Tree tree = treeMapper.toTree(treeVM);
        Tree savedTree = treeService.save(tree);
        TreeVM savedTreeVM = treeMapper.toTreeVM(savedTree);
        return ResponseEntity.status(201).body(savedTreeVM);
    }

    @PutMapping("/update")
    public ResponseEntity<TreeVM> update(@Valid @RequestBody TreeVM treeVM) {
        Tree tree = treeMapper.toTree(treeVM);
        Tree updatedTree = treeService.update(tree);
        TreeVM updatedTreeVM = treeMapper.toTreeVM(updatedTree);
        return ResponseEntity.ok(updatedTreeVM);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Long id) {
        treeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeVM> findById(@PathVariable Long id) {
        Tree tree = treeService.findById(id);
        TreeVM treeVM = treeMapper.toTreeVM(tree);
        return ResponseEntity.ok(treeVM);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TreeVM>> findAll(@RequestParam(name = "page", required = false, defaultValue = "${pagination.defaultPage}") int page, @RequestParam(name = "size", required = false, defaultValue = "${pagination.defaultPageSize}") int size) {
        Page<Tree> trees = treeService.findAll(page, size);
        Page<TreeVM> treeVMs = trees.map(treeMapper::toTreeVM);
        return ResponseEntity.ok(treeVMs);
    }



}
