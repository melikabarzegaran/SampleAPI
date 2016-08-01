package com.example.SampleAPI.models;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"after", "nextUri"})
public class Cursor
{
    private Integer after;
    private String nextUri;

    public Cursor(){}

    public Cursor(Integer after, String nextUri)
    {
        this.after = after;
        this.nextUri = nextUri;
    }

    public Integer getAfter()
    {
        return after;
    }

    public void setAfter(Integer after)
    {
        this.after = after;
    }

    public String getNextUri()
    {
        return nextUri;
    }

    public void setNextUri(String nextUri)
    {
        this.nextUri = nextUri;
    }
}