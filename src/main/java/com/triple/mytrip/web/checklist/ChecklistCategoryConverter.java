package com.triple.mytrip.web.checklist;

import com.triple.mytrip.domain.checklist.category.ChecklistCategory;
import com.triple.mytrip.web.checklist.form.ChecklistCategoryForm;

public class ChecklistCategoryConverter {

    public static ChecklistCategory formToEntity(ChecklistCategoryForm categoryForm) {
        return new ChecklistCategory(categoryForm.getName());
    }
}
