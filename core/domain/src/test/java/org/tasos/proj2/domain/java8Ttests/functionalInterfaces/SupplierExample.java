package org.tasos.proj2.domain.java8Ttests.functionalInterfaces;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class SupplierExample {
    public static void main(String[] args) {
        // Supplier that generates a sequence of random numbers
        Supplier<Integer> randomNumberSupplier = () -> (int) (Math.random() * 100);

        // Create a Stream of 5 random numbers using the Supplier
        Stream<Integer> randomNumbers = Stream.generate(randomNumberSupplier).limit(5);

        // Print the random numbers
        randomNumbers.forEach(System.out::println);
    }
}
