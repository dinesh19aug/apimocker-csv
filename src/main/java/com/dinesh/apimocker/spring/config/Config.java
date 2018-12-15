package com.dinesh.apimocker.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource (value="classpath:apimocker.properties")
@ComponentScan(basePackages = { "com.dinesh.apimocker"})
public class Config
{
    public Config(){
        System.out.println("Config class called....");
    }


}
