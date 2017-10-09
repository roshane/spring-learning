package com.aeon.sdt.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by roshane on 3/12/2017.
 */
@Entity
@Table(name = "customers")
public class Customer extends AbstractBaseEntity {

    private String name;
    private String email;
    private String age;

    protected Customer() {
    }

    public Customer(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
