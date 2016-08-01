package com.example.SampleAPI.representations;

import com.example.SampleAPI.models.Cursor;
import com.example.SampleAPI.models.User;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(propOrder = {"data", "pagination"})
public class UsersRepresentation
{
    private ArrayList<User> data;
    private Cursor pagination;

    public UsersRepresentation(){}

    public UsersRepresentation(ArrayList<User> data)
    {
        this.data = data;
    }

    public UsersRepresentation(ArrayList<User> data, Cursor pagination)
    {
        this.data = data;
        this.pagination = pagination;
    }

    public ArrayList<User> getData()
    {
        return data;
    }

    public void setData(ArrayList<User> data)
    {
        this.data = data;
    }

    public void addUser(User user)
    {
        data.add(user);
    }

    public Cursor getPagination()
    {
        return pagination;
    }

    public void setPagination(Cursor pagination)
    {
        this.pagination = pagination;
    }
}