package com.example.SampleAPI.representations;

import com.example.SampleAPI.models.User;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(propOrder = {"data"})
public class UsersRepresentation
{
    private ArrayList<User> data;

    public UsersRepresentation(){}

    public UsersRepresentation(ArrayList<User> data)
    {
        this.data = data;
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
}