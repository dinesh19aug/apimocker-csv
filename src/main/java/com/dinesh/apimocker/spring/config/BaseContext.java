package com.dinesh.apimocker.spring.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class BaseContext
{
    static ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

    public static ApplicationContext getContext(){
        return context;
    }
}
