package org.tasos.proj2.domain.java8Ttests.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionExample {
    public static void main(String[] args) {
        // A list of strings representing numbers
        List<String> stringNumbers = Arrays.asList("1", "2", "3", "4", "5");

        // Function to convert String to Integer
        Function<String, Integer> stringToInteger = str -> Integer.parseInt(str);

        // Function to multiply an Integer by 2
        Function<Integer, Integer> multiplyByTwo = num -> num * 2;

        // Function to convert Integer to String
        Function<Integer, String> integerToString = num -> "Number: " + num;

        // Using Function with Streams to apply multiple transformations
        List<String> result = stringNumbers.stream()
          .map(stringToInteger)      // Step 1: Convert String to Integer
          .map(multiplyByTwo)        // Step 2: Multiply by 2
          .map(integerToString)      // Step 3: Convert Integer back to String
          .collect(Collectors.toList());  // Collect the result into a List

        // Print the transformed list
        System.out.println("Transformed List: " + result);
    }
}
