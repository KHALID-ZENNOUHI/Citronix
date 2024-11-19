package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Field;
import org.citronix.citronix.repository.FieldRepository;
import org.citronix.citronix.web.errors.FieldNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNotNullException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.citronix.citronix.web.errors.TreesInFieldMustBeEmptyException;
import org.springframework.stereotype.Component;

@Component
public class FieldServiceImpl1 {
    private final FieldRepository fieldRepository;

    public FieldServiceImpl1(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    public Field save(Field field) {
        if (field.getId() != null) throw new IdMustBeNullException();
        if (!field.getTrees().isEmpty()) throw new TreesInFieldMustBeEmptyException();
        return fieldRepository.save(field);
    }

    public Field update(Field field) {
        findById(field.getId());
        return fieldRepository.save(field);
    }

    public Field findById(Long id) {
        if (id == null) throw new IdMustBeNotNullException();
        return fieldRepository.findById(id).orElseThrow(FieldNotFoundException::new);
    }
}
