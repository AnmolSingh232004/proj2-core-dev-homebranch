package org.tasos.proj2.domain.java8Ttests.CollectionAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CollectionEnhancements {

    public static void main(String[] args) {

        // Map sorting with stream
        Map<String, String> map = new HashMap<>();
        map.put("C", "c");
        map.put("B", "b");
        map.put("Z", "z");
        List<Map.Entry<String, String>> sortedByKey = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
        sortedByKey.forEach(System.out::println);

        // Iterate with foreach
        map.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        // Key Null checking with getOrDefault
        String val = map.getOrDefault("R", "Nah! \n");
        System.out.println(val); // prints Nah!

        // Set all values
        map.replaceAll((k, v) -> "x"); // values is "x" for all keys.
        map.forEach((k, v) -> System.out.println("New Key : " + k + " New Value : " + v));

        // Put value "x",  if key B's value absent
        Map<String, String> map2 = new HashMap<>();
        map2.put("C", "c");
        map2.put("B", null);
        map2.putIfAbsent("B", "x");
        System.out.println(map2.get("B")); // prints "x"

        // operate directly on values
        Map<String, String> map3 = new HashMap<>();
        map3.put("C", "c");
        map3.put("B", "b");
        map.compute("B", (k, v) -> v.concat(" - new "));
        System.out.println(map.get("B")); // prints "b - new"

    }


}
