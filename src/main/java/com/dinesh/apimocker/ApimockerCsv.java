package com.dinesh.apimocker;

import com.dinesh.apimocker.spring.config.ApimockServer;
import com.dinesh.apimocker.spring.config.BaseContext;
import com.dinesh.apimocker.spring.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApimockerCsv extends BaseContext
{
    public ApimockerCsv(){
        System.out.println("Apimock CSV called ....");
    }
    @Autowired
    ApimockServer server;

    public static void main(String[] args)
    {

        ApimockerCsv apimockerCsv = getContext().getBean(ApimockerCsv.class);

        apimockerCsv.server.startServer(apimockerCsv);
    }


}
