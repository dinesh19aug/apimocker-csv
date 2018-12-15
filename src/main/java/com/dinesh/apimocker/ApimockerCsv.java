package com.dinesh.apimocker;

import com.dinesh.apimocker.extensions.P2CdynamicTxnId;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class ApimockerCsv
{

    public static void main(String[] args)
    {
        //TODO Start the wiremock server
        WireMockServer wireMockServer = new WireMockServer(
                options().port(8089)
                .usingFilesUnderDirectory("C:\\Dinesh\\apimocker-config\\")
                .extensions(P2CdynamicTxnId.class) // Extension to dynamically generate txn id that is alphanumeric and 18 char long
                .extensions(new ResponseTemplateTransformer(false))//Used to turn on templating functions

        );
        wireMockServer.start();
        //wireMockServer.stop();
        //TODO Move the config settings to properties file
        //TODO Move the start server start in another file
    }
}
