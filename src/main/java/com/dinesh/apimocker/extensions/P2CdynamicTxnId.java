package com.dinesh.apimocker.extensions;

import java.util.Random;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This extension creates a random transactionId String that is alphanumeric and 18 char long
 */
public class P2CdynamicTxnId
        extends ResponseTransformer
{

    private static Logger LOG = LoggerFactory.getLogger(P2CdynamicTxnId.class);

    @Override
    public Response transform(Request request,
                              Response response,
                              FileSource fileSource,
                              Parameters parameters)
    {
        String finalResponseAsString = updateTxnIdInResponse(response);
        return Response.Builder
                       .like(response)
                       .but()
                       .body(finalResponseAsString)
                       .build();
    }

    private String updateTxnIdInResponse(Response response)
    {
        OrderedJSONObject responseObject = null;
        try
        {
            responseObject = new OrderedJSONObject(response.getBodyAsString());
            responseObject.put("transactionId", this.getDynamicTransactionId());
        }
        catch (JSONException e)
        {
            LOG.error("Error converting response json string to json object");
        }
        return responseObject.toString();
    }

    private String getDynamicTransactionId()
    {
        String saltChar = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * saltChar.length());
            salt.append(saltChar.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @Override
    public String getName()
    {
        return "dynamic-txn-id";
    }

    @Override
    public boolean applyGlobally()
    {
        return false;
    }


}
