package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    Page<Field> findAll(Pageable pageable);
}
