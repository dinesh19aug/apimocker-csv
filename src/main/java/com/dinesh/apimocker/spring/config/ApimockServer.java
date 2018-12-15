package com.dinesh.apimocker.spring.config;

import com.dinesh.apimocker.ApimockerCsv;
import com.dinesh.apimocker.extensions.P2CdynamicTxnId;
import com.dinesh.apimocker.util.Constants;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Component
public class ApimockServer
{
    @Autowired
    Constants constants;

    public ApimockServer(){
        System.out.println("ApiMockServer called...");
    }
    public void startServer(ApimockerCsv apimockerCsv)
    {
        @SuppressWarnings("unchecked")
        WireMockServer wireMockServer = new WireMockServer(
                options().port(Integer.parseInt(constants.PORT_NUMBER))
                         .usingFilesUnderDirectory(constants.MAPPING_DIRECTORY)
                         .extensions(
                                 P2CdynamicTxnId.class) // Extension to dynamically generate txn id that is alphanumeric and 18 char long
                         .extensions(new ResponseTemplateTransformer(false))//Used to turn on templating functions

        );
        wireMockServer.start();
        //wireMockServer.stop();
        
    }
}
