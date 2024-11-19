package org.citronix.citronix.service;

import org.citronix.citronix.domain.Field;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface FieldService {

        Field save(Field field);

        Field update(Field field);

        Field findById(Long id);

        void delete(Long id);

        Page<Field> findAll(int page, int size);

}
