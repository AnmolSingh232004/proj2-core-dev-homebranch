package org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator;

import java.util.List;

import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityResponse2;

public interface Accumulator<T> {

    default void accumulate(DayActivityResponse2 t) {
    }

    default List<T> getTotals(String key) {
        return null;
    }

    default T getTotal(String key) {
        return null;
    }

//	String key(DayActivityDTO t);
//
//	String childKey(DayActivityDTO t);

}
