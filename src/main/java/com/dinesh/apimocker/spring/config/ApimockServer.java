package com.dinesh.apimocker.spring.config;

import com.dinesh.apimocker.extensions.CSVExtension;
import com.dinesh.apimocker.extensions.DynamicTxnIdExtension;
import com.dinesh.apimocker.extensions.service.CsvExtensionService;
import com.dinesh.apimocker.util.Constants;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Component
public class ApimockServer
{
    @Autowired
    Constants constants;

    @Autowired
    CSVExtension csvExtension;

    @Autowired
    CsvExtensionService csvExtensionService;

    public ApimockServer(){
        System.out.println("ApiMockServer called...");
    }
    public void startServer()
    {
        @SuppressWarnings("unchecked")
        WireMockServer wireMockServer = new WireMockServer(
                options().port(Integer.parseInt(constants.PORT_NUMBER))
                         .usingFilesUnderDirectory(constants.MAPPING_DIRECTORY)
                         // Extension to dynamically generate txn id that is alphanumeric and 18 char long
                         .extensions(DynamicTxnIdExtension.class)
                         //Used to turn on templating functions
                         .extensions(new ResponseTemplateTransformer(false))
                        .extensions(new CSVExtension(csvExtensionService))

        );
        wireMockServer.start();
        //wireMockServer.stop();

    }
}
