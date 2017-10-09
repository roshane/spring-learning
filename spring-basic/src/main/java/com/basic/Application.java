package com.basic;

import com.basic.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by roshane on 3/28/2017.
 */
public class Application {

    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        List<List<String>> lists = Arrays.asList(Arrays.asList("hello"), Arrays.asList("world"), Arrays.asList("roshane"));

        List<String> collect = lists.stream()
                .flatMap(strings -> strings.stream().map(String::toUpperCase))
                .collect(Collectors.toList());

        System.out.println(collect);

    }
}
