package com.solar.calm.web.rest.util;

import com.solar.calm.config.Constants;
import com.solar.calm.web.rest.param.BaseParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

public class QueryHelper {

    public static PageRequest getPageRequest(BaseParams param) {
        return new PageRequest(param.getPageNo() == null ? 0 : param.getPageNo() - 1, param.getPageSize(),
            QueryHelper.getSort(param.getSortBy()));
    }

    public static Sort getDefaultSort() {
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        return new Sort(order);
    }

    public static Sort getSort(String param, Sort.Direction direction) {
        Sort.Order order = new Sort.Order(direction, param);
        return new Sort(order);
    }

    public static Sort getSort(TreeMap<String, Sort.Direction> map) {
        List<Sort.Order> orderList = new ArrayList<>();
        for (Map.Entry<String, Sort.Direction> entry : map.entrySet()) {
            Sort.Order order = new Sort.Order(entry.getValue(), entry.getKey());
            orderList.add(order);
        }
        return new Sort(orderList);
    }

    public static Sort getSort(String sortBy) {
        return StringUtils.isBlank(sortBy) ? getDefaultSort()
            : new Sort(Arrays.asList(sortBy.split(Constants.COMMA)).stream()
            .map((orders) -> getOrder(orders.split(Constants.COLON))).collect(Collectors.toList()));
    }

    private static Sort.Order getOrder(String[] orders) {
        return new Sort.Order(Sort.Direction.fromString(orders[1]), orders[0]);
    }
}
