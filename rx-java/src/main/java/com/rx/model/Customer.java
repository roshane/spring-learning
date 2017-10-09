package com.rx.model;

/**
 * Created by roshane on 5/18/2017.
 */
public class Customer {

    private int age;
    private String firstName;
    private String lastName;
    private String email;

    public Customer(int age, String firstName, String lastName, String email) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
