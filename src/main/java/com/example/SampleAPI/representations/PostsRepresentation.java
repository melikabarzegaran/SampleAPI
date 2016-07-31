package com.example.SampleAPI.representations;

import com.example.SampleAPI.models.Post;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(propOrder = {"data"})
public class PostsRepresentation
{
    private ArrayList<Post> data;

    public PostsRepresentation(){}

    public PostsRepresentation(ArrayList<Post> data)
    {
        this.data = data;
    }

    public ArrayList<Post> getData()
    {
        return data;
    }

    public void setData(ArrayList<Post> data)
    {
        this.data = data;
    }

    public void addPost(Post post)
    {
        data.add(post);
    }
}