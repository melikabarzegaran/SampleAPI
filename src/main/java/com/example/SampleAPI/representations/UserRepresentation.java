package com.example.SampleAPI.representations;

import com.example.SampleAPI.models.User;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"data"})
public class UserRepresentation
{
    private User data;

    public UserRepresentation(){}

    public UserRepresentation(User data)
    {
        this.data = data;
    }

    public User getData()
    {
        return data;
    }

    public void setData(User data)
    {
        this.data = data;
    }
}