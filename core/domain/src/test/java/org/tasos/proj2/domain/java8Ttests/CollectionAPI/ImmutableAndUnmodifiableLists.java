package org.tasos.proj2.domain.java8Ttests.CollectionAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImmutableAndUnmodifiableLists {

    public static void main(String[] args) {
        var list1 = new ArrayList<String>(Arrays.asList("a", "b", "c"));
        var unmodList = Collections.unmodifiableList(list1);
        var list3 = List.copyOf(unmodList);
        var list4 = List.copyOf(list3);

        System.out.println(unmodList == list3);

        System.out.println(list3 == list4);

        // Can add things in original list (unmodifiable also affected)
        list1.add("d");
        unmodList.forEach(System.out::println);

        // But can't add things to unmodifiable itself (UnsupportedOperationException)
//        unmodList.add("y");

        System.out.println();


        list3.forEach(System.out::println);
    }
}
