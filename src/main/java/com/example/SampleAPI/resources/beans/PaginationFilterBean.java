package com.example.SampleAPI.resources.beans;

import javax.ws.rs.QueryParam;

public class PaginationFilterBean
{
    public final static String CURSOR = "cursor";
    public final static String NUMBER = "number";
    private @QueryParam(CURSOR) int cursor;
    private @QueryParam(NUMBER) int number;

    public int getCursor()
    {
        return cursor;
    }

    public void setCursor(int cursor)
    {
        this.cursor = cursor;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }
}