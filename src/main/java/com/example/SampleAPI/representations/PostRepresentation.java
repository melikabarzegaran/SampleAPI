package com.example.SampleAPI.representations;

import com.example.SampleAPI.models.Post;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"data"})
public class PostRepresentation
{
    private Post data;

    public PostRepresentation(){}

    public PostRepresentation(Post data)
    {
        this.data = data;
    }

    public Post getData()
    {
        return data;
    }

    public void setData(Post data)
    {
        this.data = data;
    }
}