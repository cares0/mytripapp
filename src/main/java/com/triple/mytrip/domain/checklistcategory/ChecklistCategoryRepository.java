package com.triple.mytrip.domain.checklistcategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistCategoryRepository extends JpaRepository<ChecklistCategory, Long>, ChecklistCategoryRepositoryCustom {


}
