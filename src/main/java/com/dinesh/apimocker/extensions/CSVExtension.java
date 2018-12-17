package com.dinesh.apimocker.extensions;

import com.dinesh.apimocker.extensions.service.CsvExtensionService;
import com.dinesh.apimocker.util.Constants;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CSVExtension extends ResponseTransformer
{
    public CSVExtension(){
        System.out.println("CSVExtension called ...");

    }

    public CSVExtension(CsvExtensionService csvExtensionService)
    {
        this.service = csvExtensionService;
    }

    CsvExtensionService service;

    public Response transform(Request request,
                              Response response,
                              FileSource fileSource,
                              Parameters parameters)
    {
        String finalResponse = service.createResponse(request, response,parameters);
        return Response.Builder
                       .like(response)
                       .but()
                       .body(finalResponse)
                       .build();
    }

    public String getName()
    {
        return "csv-reader";
    }

    @Override
    public boolean applyGlobally()
    {
        return false;
    }

}
