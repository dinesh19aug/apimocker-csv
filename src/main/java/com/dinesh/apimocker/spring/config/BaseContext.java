package com.dinesh.apimocker.spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


public abstract class BaseContext
{
    static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static ApplicationContext getContext(){
        return context;
    }
}
