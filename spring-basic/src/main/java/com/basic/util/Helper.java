package com.basic.util;

import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by roshane on 3/28/2017.
 */
@Component
public class Helper implements InitializingBean {

    @Autowired
    private Logger logger;

    public void init() {
        System.out.printf("Initializing %s \n", Helper.class.getSimpleName());
    }

    public void destroy() {
        System.out.printf("Destroying %s \n", Helper.class.getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(logger);
        logger.info("------------------ huree ------------------");
    }
}
