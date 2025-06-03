package org.tasos.proj2.domain.javaversions;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Java9Tests {

    @Test
    public void testTryWithResources() {

        // With Java 9
        try (Scanner scanner = new Scanner(new File("C:\\Users\\tioan\\projects\\proj2\\core\\domain\\src\\test\\java\\resources\\test.txt"))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        // With older Java - more lines, init, closing
//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File("test.txt"));
//            while (scanner.hasNext()) {
//                System.out.println(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            if (scanner != null) {
//                scanner.close();
//            }
//        }
    }

}
