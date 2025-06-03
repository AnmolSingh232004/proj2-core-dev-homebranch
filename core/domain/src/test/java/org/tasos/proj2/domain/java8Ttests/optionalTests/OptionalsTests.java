package org.tasos.proj2.domain.java8Ttests.optionalTests;

import java.util.Optional;

public class OptionalsTests {

    public static void main(String[] args) {

        // Optional - Map
        String str = "str";
        Optional<String> opStr = Optional.of(str);
        System.out.println("Optional map: " +  opStr.map(String::toUpperCase));

        // Optional of Optional - Map vs Flat Map
        // Flat Map leaves only one Optional
        Optional<Optional<String>> opStr22 = Optional.of(Optional.of("str"));
        System.out.println("Optional 2 map: " +  opStr22.map(opStr2 -> opStr2.map(String::toUpperCase)));
        System.out.println("Optional 2 flat map: " +  opStr22.flatMap(opStr2 -> opStr2.map(String::toUpperCase)));


        // Optional - Nullable - NPE
        String answer2 = null;
        System.out.println("ofNullable on Empty Optional: " + Optional.ofNullable(answer2));
        // java.lang.NullPointerException
//        System.out.println("ofNullable on Non-Empty Optional: " + Optional.of(answer2));

        // Optional - Filter
        Optional<String> gender = Optional.of("MALE");
        System.out.println(gender.filter(g -> g.equals("male"))); //Optional.empty
        System.out.println(gender.filter(g -> g.equalsIgnoreCase("MALE"))); //Optional[MALE]

        // Optional - orElse
        Optional<String> str1 = Optional.of("str");
        Optional<String> str2 = Optional.empty();
//        Optional<String> str3 = Optional.of(null);
        System.out.println(" Print optional str1 value if exists, or something else: "+str1.orElse("else"));
        System.out.println(" Print optional str2 value if exists, or something else: "+str2.orElse("else"));
    }
}
