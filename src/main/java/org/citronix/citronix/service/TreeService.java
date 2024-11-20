package org.citronix.citronix.service;

import org.citronix.citronix.domain.Tree;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface TreeService {
    Tree save(Tree tree);

    Tree update(Tree tree);

    Tree findById(Long id);

    void delete(Long id);

    Page<Tree> findAll(int page, int size);

    Boolean isPlantingSeason(LocalDateTime plantingDate);
}
