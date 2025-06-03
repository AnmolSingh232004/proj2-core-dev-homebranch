package org.tasos.proj2.domain.java8Ttests.functionalInterfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

class ConsImpl implements Consumer<Integer>
{
    public void accept (Integer i)
    {
        System.out.println(i);
    }
}

public class Consumer_DemoForEach {
    public static void main(String args[]) {
        List<Integer> values = Arrays.asList(4, 5, 6, 7, 8);
        Consumer<Integer> c = new ConsImpl();
        values.forEach(c);

        // Or, simplest way (still a consumer interface)
        // Using only iterable/arraylist
        //values.forEach(i-> System.out.println(i));
    }
}