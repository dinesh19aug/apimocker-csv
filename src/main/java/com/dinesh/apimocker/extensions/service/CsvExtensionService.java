package com.dinesh.apimocker.extensions.service;


import java.util.LinkedHashMap;
import java.util.List;
import java.io.*;

import com.dinesh.apimocker.extensions.vo.ParamCsvFileVO;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.Response;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class CsvExtensionService
{

    public CsvExtensionService()
    {
        System.out.println("CsvExtensionService called ....");
    }

    private static Logger LOG = LoggerFactory.getLogger(CsvExtensionService.class);

    @Autowired
    ParamCsvFileVO paramCsvFileVO;

    public String createResponse(Request request,
                                 Response response,
                                 Parameters parameters)
    {
        OrderedJSONObject jsonObject = transformResponseToJson(response);
        this.extractCsvFileInfoFromParams(parameters);
        return this.updateJsonObjectFromCsv(jsonObject).toString();

    }

    private OrderedJSONObject updateJsonObjectFromCsv(OrderedJSONObject jsonObject)
    {
        List<String[]> csvRows =this.getCsvRow();
        System.out.printf("");
        try
        {
            jsonObject.put("transactionId",((String[])csvRows.get(1))[0]);
            ((OrderedJSONObject)((JSONArray)jsonObject.get("systemErrors")).get(0)).put("code", ((String[])csvRows.get(1))[1]);
            ((OrderedJSONObject)((JSONArray)jsonObject.get("systemErrors")).get(0)).put("description", ((String[])csvRows.get(1))[2]);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private List<String[]> getCsvRow()
    {
        List<String[]> allRows;
        String filePath = paramCsvFileVO.getDirectory() + "\\" + paramCsvFileVO.getName();
        CsvParser parser = getCsvParser();
        allRows = parser.parseAll(getReader(filePath));

        return allRows;
    }

    private CsvParser getCsvParser()
    {
        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");
        return new CsvParser(settings);
    }

    public InputStreamReader getReader(String relativePath)
    {
        FileReader fileReader = null;
        try
        {
            fileReader = new FileReader(relativePath);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return fileReader;
    }

    private void extractCsvFileInfoFromParams(Parameters parameters)
    {
        String fileName = (String) ((LinkedHashMap) parameters.get("file")).get("name");
        String filePath = (String) ((LinkedHashMap) parameters.get("file")).get("directory");
        List<String> lookUpColumns = (List<String>) ((LinkedHashMap) parameters.get("file")).get("lookupColumns");
        paramCsvFileVO.setName(fileName);
        paramCsvFileVO.setDirectory(filePath);
        paramCsvFileVO.setLookupColumnList(lookUpColumns);
    }


    private OrderedJSONObject transformResponseToJson(Response response)
    {
        OrderedJSONObject responseObject = null;
        try
        {
            responseObject = new OrderedJSONObject(response.getBodyAsString());

        }
        catch (JSONException e)
        {
            LOG.error("Error converting response json string to json object");
        }
        return responseObject;
    }
}
