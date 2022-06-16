package com.triple.mytrip.domain.checklist.category;

import com.triple.mytrip.domain.checklist.ChecklistRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistCategoryRepository extends JpaRepository<ChecklistCategory, Long> {
}
