package com.example.SampleAPI.representations;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"type", "code", "message"})
public class ErrorRepresentation
{
    private String type;
    private Integer code;
    private String message;

    public ErrorRepresentation(){}

    public ErrorRepresentation(String type, Integer code, String message)
    {
        this.type = type;
        this.code = code;
        this.message = message;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
