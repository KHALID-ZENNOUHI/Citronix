package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreeRepository extends JpaRepository<Tree, Long> {

    Page<Tree> findAll(Pageable pageable);

}
