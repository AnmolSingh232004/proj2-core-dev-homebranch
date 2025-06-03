package org.tasos.proj2.domain.java8Ttests.CollectionAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IteratorsTests {

    public static void main(String[] args) {
        var list1 = new ArrayList<String>(Arrays.asList("a", "b", "c"));

        for (String list1Item : list1) {
            if (list1Item.equalsIgnoreCase("a")) {
                list1.remove(list1Item);
            }

        }
    }
}
