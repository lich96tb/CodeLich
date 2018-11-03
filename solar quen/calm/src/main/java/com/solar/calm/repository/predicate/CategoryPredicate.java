package com.solar.calm.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.solar.calm.domain.QCategory;
import com.solar.calm.web.rest.param.CategoryParams;

public class CategoryPredicate {

    public static Predicate filter(CategoryParams search) {
        Predicate predicate = null;

        QCategory category = QCategory.category;
        predicate = category.id.isNotNull();
        if (search.getName() != null) {
            predicate = ((BooleanExpression) predicate).and(category.name.eq(search.getName()));
        }
        if (search.getParentId() != null) {
            predicate = ((BooleanExpression) predicate).and(category.parentId.eq(search.getParentId()));
        } else {
            predicate = ((BooleanExpression) predicate).and(category.parentId.isNull());
        }
        return predicate;
    }
}
