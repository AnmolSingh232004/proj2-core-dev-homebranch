package org.tasos.proj2.domain.javaversions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java11Tests {

    private String str = "str1";

    // doesn't work
    @Test
    public void testVolatile() throws Exception {

        //Thread 1 write
        Thread t1 = new Thread(() -> {
            str = "str-t1";
        });
        t1.start();

        //Thread 2 get updated
        Thread t2 = new Thread(() -> {
            // code goes here.
            System.out.println("str = " + str);
        });
        t2.start();

    }

    @Test
    public void testNotPredicate() throws Exception {
        Stream.of("tasos", "tasoss", "tasosss", "thanasis", "thanasis2")
                .filter(Predicate.not(s -> s.contains("than")))
                .forEach(s -> System.out.println("element: " + s));

    }


    //Local-Variable Syntax for Lambda
    @Test
    public void testSyntax() throws Exception {
        List<String> sampleList = Arrays.asList("Java", "Kotlin");

        String resultString = sampleList.stream()
                .map((@Nonnull var x) -> x.toUpperCase())
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(resultString,"JAVA, KOTLIN");
    }
}
