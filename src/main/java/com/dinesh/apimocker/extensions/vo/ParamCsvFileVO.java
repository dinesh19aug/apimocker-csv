package com.dinesh.apimocker.extensions.vo;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ParamCsvFileVO
{
    private String name;
    private String directory;
    private List<String> lookupColumnList;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDirectory()
    {
        return directory;
    }

    public void setDirectory(String directory)
    {
        this.directory = directory;
    }

    public List<String> getLookupColumnList()
    {
        return lookupColumnList;
    }

    public void setLookupColumnList(List<String> lookupColumnList)
    {
        this.lookupColumnList = lookupColumnList;
    }
}
