package com.solar.calm.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Ids string transform to iterable helper.
 */
@Component
public class Transformer {


    public List<Long> idsStr2List(String idsStr) {
        return Arrays.stream(idsStr.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }

    public String IdList2IdsStr(List<Long> list) {
        return list.stream().map(String::valueOf).collect(Collectors.joining(","));
    }


    public <T> Set<T> list2Set(List<T> objects) {
        return new HashSet<>(objects);
    }


    public <T> List<T> set2List(Set<T> objects) {
        return new ArrayList<>(objects);
    }


    @SuppressWarnings("unchecked")
    public Page poPage2VO(List content, Pageable pageable, Long totalElements) {
        return new PageImpl<>(content, pageable, totalElements);
    }


    public <T> T param2PO(Class<T> type, Object param, T po
    ) {
        // Init createdBy, lastModifiedBy
        Long createdBy;
        Long lastModifiedBy;
        // Init transformer
//		Field idField = type.getDeclaredField(CommonsConstant.ID);
//		idField.setAccessible(true);
//		Field createdByField = type.getDeclaredField(CommonsConstant.CREATED_BY);
//		createdByField.setAccessible(true);
//		Field lastModifiedByField = type.getDeclaredField(CommonsConstant.LAST_MODIFIED_BY);
//		lastModifiedByField.setAccessible(true);
//		Field lastModifiedDateField = type.getDeclaredField(CommonsConstant.LAST_MODIFIED_DATE);
//		lastModifiedDateField.setAccessible(true);
        Date now = new Date();
//		if (idField.get(po) == null) {
//			createdBy = currentUser.getId();
//			lastModifiedBy = createdBy;
//		} else {
//			createdBy = (Long) createdByField.get(po);
//			lastModifiedBy = currentUser.getId();
//		}
        // Set param.
        BeanUtils.copyPropertiesIgnoreNull(param, po);
//		createdByField.set(po, createdBy);
//		lastModifiedByField.set(po, lastModifiedBy);
//		lastModifiedDateField.set(po, now);
        return po;
    }


    @SuppressWarnings("unchecked")
    public List pos2VOs(Class<?> type, List pos) throws Exception {
        List voList = new ArrayList();
        for (Object po : pos) {
            Object vo = po2VO(type, po);
            voList.add(vo);
        }
        return voList;
    }

    public <T> T po2VO(Class<T> clazz, Object po) throws InstantiationException, IllegalAccessException {
        T vo = clazz.newInstance();
        BeanUtils.copyPropertiesIgnoreNull(po, vo);
        return vo;
    }

}
