package com.dinesh.apimocker.spring.config;

import com.dinesh.apimocker.extensions.CSVExtension;
import com.dinesh.apimocker.extensions.service.CsvExtensionService;
import org.springframework.context.annotation.*;

@Configuration
@PropertySource (value="classpath:apimocker.properties")
@ComponentScan(basePackages = { "com.dinesh.apimocker"})
public class Config
{
    public Config(){
        System.out.println("Config class called....");
    }

    @Bean
    @Scope(value = "prototype")
    public CsvExtensionService getCsvExtensionService(){
        return new CsvExtensionService();
    }

    @Bean
    @Scope(value = "prototype")
    public CSVExtension getCsvExtension(){
        return new CSVExtension(getCsvExtensionService());
    }


}
