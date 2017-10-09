package com.aeon.sdt.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;

/**
 * Created by roshane on 3/12/2017.
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

}
