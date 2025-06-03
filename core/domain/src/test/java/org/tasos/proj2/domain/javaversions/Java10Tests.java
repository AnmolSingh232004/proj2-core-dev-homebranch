package org.tasos.proj2.domain.javaversions;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Java10Tests {

    @Test
    public void testOptionalElseThrow() throws Exception {

        Exception thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            String str = null;
            Optional<String> strOpt = Optional.ofNullable(str);

            String val =  strOpt.orElseThrow(()-> {
                return new RuntimeException("no value present in Optional object");
            });
        });

        Assertions.assertEquals("no value present in Optional object", thrown.getMessage());
    }

    @Test
    public void testVar() throws Exception {

        var str = "str";

        // Benefit: use "var" instead of long "Map<String, Map<String, Integer>>"
        var complexType = returnAComplexType();

        System.out.println("a complex map's element: " +complexType.get("USA").get("str1"));

    }

    Map<String, Map<String, Integer>> returnAComplexType() {
        ImmutableMap<String, Map<String, Integer>> immutableMap
                = ImmutableMap.of("USA", ImmutableMap.of("str1", 4), "Costa Rica", ImmutableMap.of("str2", 5));
        return immutableMap;
    }
}
