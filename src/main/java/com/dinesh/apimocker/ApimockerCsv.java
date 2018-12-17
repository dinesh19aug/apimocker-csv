package com.dinesh.apimocker;

import com.dinesh.apimocker.extensions.CSVExtension;
import com.dinesh.apimocker.extensions.service.CsvExtensionService;
import com.dinesh.apimocker.extensions.vo.ParamCsvFileVO;
import com.dinesh.apimocker.spring.config.ApimockServer;
import com.dinesh.apimocker.spring.config.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
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

        apimockerCsv.server.startServer();
    }


}
