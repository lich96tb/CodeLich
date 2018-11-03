package com.solar.calm.repository.predicate;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.solar.calm.domain.QSection;
import com.solar.calm.web.rest.param.SectionParams;

public class SectionPredicate {

    public static Predicate filter(SectionParams search) {
        Predicate predicate = null;

        QSection section = QSection.section;
        predicate = section.id.isNotNull();
        if (search.getName() != null) {
            predicate = ((BooleanExpression) predicate).and(section.name.eq(search.getName()));
        }
        return predicate;
    }
}
