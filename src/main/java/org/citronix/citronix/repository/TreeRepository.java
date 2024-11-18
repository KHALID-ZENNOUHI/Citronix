package org.citronix.citronix.repository;

import org.citronix.citronix.domain.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}
