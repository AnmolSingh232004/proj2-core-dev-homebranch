package org.tasos.proj2.domain.java8Ttests.streamTests;

import org.junit.jupiter.api.Test;
import org.tasos.proj2.domain.activity.ActivityAggregate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

/**
 * Stream API playground
 * Testing filtering,sorting,iterating of Activities / DayActivities, with Stream API
 */
public class StreamsTests {

    @Test
    public void testSorting() {
        List<ActivityAggregate> sortedList = getActivities()
                .stream()
//                .sorted(Comparator.comparingLong(act -> act.getId()))
                .sorted((act1, act2) -> Long.compare(act2.getId(), act1.getId()))
//                .sorted(Comparator.comparing(StreamsTests::getMove, Comparator.comparing(Math::abs)).reversed())
                .limit(5)
                .collect(Collectors.toList());

        assertThat(sortedList.get(0).getId(), is(equalTo(11L)));
        assertThat(sortedList.get(1).getId(), is(equalTo(10L)));
        assertThat(sortedList.get(2).getId(), is(equalTo(9L)));
        assertThat(sortedList.get(3).getId(), is(equalTo(8L)));
        assertThat(sortedList.get(4).getId(), is(equalTo(7L)));
    }

    @Test
    public void testReduce() {
        String result = Stream.of("hello", "world", "world2", "world3", "world4")
                .reduce("", (a, b) -> b + "-" + a);

        System.out.println(result);

        assertThat(result,is(equalTo("world4-world3-world2-world-hello-")));

    }


    @Test
    public void testListJava8Enhancements() {
        List<Integer> intList = new ArrayList<>();
        intList.add(4);
        intList.add(44);
        intList.add(444);

       intList.forEach(i ->  System.out.println(i));


       //RemoveIf
        System.out.println("\n RemoveIf, remove elements < 50:");
       intList.removeIf( i -> i<50);
        intList.forEach(i ->  System.out.println(i));
        // add back
        intList = List.of(4,44,444);

        // Iterable vs Iterator - forEachRemaining
        System.out.println("\n Iterator.next and then forEachRemaining:");
        Iterator it = intList.listIterator();
        it.next(); // remove first from iterator
        it.forEachRemaining(i -> System.out.println(i));

    }

    @Test
    public void testHashMapJava8Enhancements() {
        Map<String, Integer> map = new HashMap<>();
        map.put("C", 1);
        map.put("B", 3);
        map.put("Z", 2);
        List<Map.Entry<String, Integer>> sortedByKey = map.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toList());
        sortedByKey.forEach(System.out::println);

        System.out.println("\n");
        List<Map.Entry<String, Integer>> sortedByValue = map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());
        sortedByValue.forEach(System.out::println);

        // Iterate
        System.out.println("\n");
        map.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        // Get key value if exists, else default
        System.out.println("\n");
         System.out.println("Key H value = " + map.getOrDefault("H", 555));

         // Replace all
        map.replaceAll((k, v) -> 999); // values is "x" for all keys.
        System.out.println("\n Replaced all values:");
        map.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        // Update a value
        System.out.println("\n  Update B's value:");
        map.compute("B", (k, v) -> v + 1);
        System.out.println(map.get("B"));
    }


    private List<ActivityAggregate> getActivities() {
        // ActType GYM
        ActivityAggregate act1 = new ActivityAggregate.ActivityBuilder()
                .withId(1L)
                .withTitle("UpperChest_S")
                .withActivitySubType("STRENGTH")
                .withActivityType(1L, "GYM", "GYM desc")
                .build();
        ActivityAggregate act2 = new ActivityAggregate.ActivityBuilder()
                .withId(2L)
                .withTitle("LowerChest_S")
                .withActivitySubType("STRENGTH")
                .withActivityType(1L, "GYM", "GYM desc")
                .build();
        ActivityAggregate act3 = new ActivityAggregate.ActivityBuilder()
                .withId(3L)
                .withTitle("BackShoulders_H")
                .withActivitySubType("HYPERTROPHY")
                .withActivityType(1L, "GYM", "GYM desc")
                .build();
        ActivityAggregate act4 = new ActivityAggregate.ActivityBuilder()
                .withId(4L)
                .withTitle("Legs_H")
                .withActivitySubType("HYPERTROPHY")
                .withActivityType(1L, "GYM", "GYM desc")
                .build();

        // ActType CAR
        ActivityAggregate act5 = new ActivityAggregate.ActivityBuilder()
                .withId(5L)
                .withTitle("GAS_SERVICE")
                .withActivitySubType("GAS")
                .withActivityType(2L, "CAR", "CAR desc")
                .build();
        ActivityAggregate act6 = new ActivityAggregate.ActivityBuilder()
                .withId(6L)
                .withTitle("GAS_MONTHLY")
                .withActivitySubType("GAS")
                .withActivityType(2L, "CAR", "CAR desc")
                .build();
        ActivityAggregate act7 = new ActivityAggregate.ActivityBuilder()
                .withId(7L)
                .withTitle("TIRES_BROKEN")
                .withActivitySubType("TIRES")
                .withActivityType(2L, "CAR", "CAR desc")
                .build();
        ActivityAggregate act8 = new ActivityAggregate.ActivityBuilder()
                .withId(8L)
                .withTitle("TIRES_TIMELYCHANGE")
                .withActivitySubType("TIRES")
                .withActivityType(2L, "CAR", "CAR desc")
                .build();

        // ActType HOUSE
        ActivityAggregate act9 = new ActivityAggregate.ActivityBuilder()
                .withId(9L)
                .withTitle("BATHROOM_LIGHTS")
                .withActivitySubType("ELECTRICALS")
                .withActivityType(3L, "HOUSE", "HOUSE desc")
                .build();
        ActivityAggregate act10 = new ActivityAggregate.ActivityBuilder()
                .withId(10L)
                .withTitle("DESK_NEW")
                .withActivitySubType("FURNITURE")
                .withActivityType(3L, "HOUSE", "HOUSE desc")
                .build();
        ActivityAggregate act11 = new ActivityAggregate.ActivityBuilder()
                .withId(11L)
                .withTitle("WATER_PAYMENT")
                .withActivitySubType("TIMELY_EXPENSES")
                .withActivityType(3L, "HOUSE", "HOUSE desc")
                .build();

        return List.of(act1, act2, act3, act4, act5, act6, act7, act8, act9, act10, act11);
    }

    // Various
    public static void main(String[] args) {

        // Simple List of integers filtering
        List<Integer> aList = Arrays.asList(6,88,23,14,17,12,32,51,79,94);
        aList = aList.stream().filter(num -> num %2 == 0).collect(Collectors.toList());
        aList.stream().forEach(i -> System.out.println(i));

        // Generator - supplier
//        Stream<Integer> intStream = Stream.generate(() ->  new Random().nextInt(20));
//        intStream.forEach(i -> System.out.println(i));

        // Flatmap
        // using flatmap() to flatten this list
        // Prints: 1,2,3,4,5,6,7,8
        List<List<Integer> > number = new ArrayList<>();

        // adding the elements to number arraylist
        number.add(Arrays.asList(1, 2));
        number.add(Arrays.asList(3, 4));
        number.add(Arrays.asList(5, 6));
        number.add(Arrays.asList(7, 8));

        List<Integer> flatList
                = number.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        System.out.println("\n");
        flatList.forEach(i -> System.out.println(i));


    }
}
