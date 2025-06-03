package org.tasos.proj2.domain.concurrency;

import java.util.Scanner;

class MyThread extends Thread {
    private boolean running = true;   //non-volatile keyword

    public void run() {
        while (running) {
            System.out.println("hello");
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class ConcurrencyTests {

    public static void main(String[] args) {
        MyThread obj = new MyThread();
        obj.start();

        Scanner input = new Scanner(System.in);
        input.nextLine();
        obj.shutdown();
    }
}