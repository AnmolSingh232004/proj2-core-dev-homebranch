package org.tasos.proj2.applicationlogic.services.implementation.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {

    @Override
    default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            // Implement our own exception handling logic here..
            System.out.println("handling an exception...");
            throw new RuntimeException(e);
        }
    }

    void acceptThrows(T elem) throws Exception;

}