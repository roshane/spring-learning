package com.aeon.aop.comp;

/**
 * Created by roshane on 1/25/2018.
 */
public class SimpleResourceClientTest {

    public static void main(String[] args) {
        try(SimpleResourceClient src=new SimpleResourceClient()){
            src.doSomething();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
