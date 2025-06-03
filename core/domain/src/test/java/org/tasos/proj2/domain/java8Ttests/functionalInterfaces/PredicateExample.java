package org.tasos.proj2.domain.java8Ttests.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateExample {
    public static void main(String[] args) {
        // A list of integers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Predicate to test if a number is even
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Using Predicate with Streams to filter even numbers
        List<Integer> evenNumbers = numbers.stream()
          .filter(isEven)  // Apply the Predicate
          .collect(Collectors.toList());  // Collect the result into a List

        // Print the filtered even numbers
        System.out.println("Even Numbers: " + evenNumbers);
    }
}
