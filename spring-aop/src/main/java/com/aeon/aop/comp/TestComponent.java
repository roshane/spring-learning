package com.aeon.aop.comp;

import com.aeon.aop.aspect.EnableAudit;
import org.springframework.stereotype.Component;

/**
 * Created by roshane on 1/3/2018.
 */
@Component
public class TestComponent {

    @EnableAudit("com.aeon.aop.comp.TestComponent#doSomething")
    public void doSomething(){

    }
}
