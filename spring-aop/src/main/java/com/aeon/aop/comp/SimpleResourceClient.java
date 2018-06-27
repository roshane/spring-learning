package com.aeon.aop.comp;

/**
 * Created by roshane on 1/25/2018.
 */
public class SimpleResourceClient implements AutoCloseable {

    public SimpleResourceClient() {
        System.out.println("creating the resource");
    }

    @Override
    public void close() throws Exception {
        System.out.println("closing the resource");
    }

    public void doSomething() {
        System.out.println("doing something...");
    }
}
