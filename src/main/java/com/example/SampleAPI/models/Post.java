package com.example.SampleAPI.models;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.UUID;

@XmlType(propOrder = {"postId", "author", "message", "created", "links"})
public class Post
{
    private String postId;
    private String author;
    private String message;
    private Date created;
    private ArrayList<Link> links;

    public Post()
    {
        this(null, null);
    }

    public Post(String author, String message)
    {
        this(UUID.randomUUID().toString(), author, message);
    }

    public Post(String postId, String author, String message)
    {
        this(postId, author, message, new Date(Calendar.getInstance().getTime().getTime()));
    }

    public Post(String postId, String author, String message, Date created)
    {
        this(postId, author, message, created, new ArrayList<Link>());
    }

    public Post(String postId, String author, String message, Date created, ArrayList<Link> links)
    {
        this.postId = postId;
        this.author = author;
        this.message = message;
        this.created = created;
        this.links = links;
    }

    public String getPostId()
    {
        return postId;
    }

    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public ArrayList<Link> getLinks()
    {
        return links;
    }

    public void setLinks(ArrayList<Link> links)
    {
        this.links = links;
    }

    public void addLink(Link link)
    {
        links.add(link);
    }

    public void addSelfAndAuthor(String selfUri, String authorUri)
    {
        links.add(new Link("self", selfUri));
        links.add(new Link("author", authorUri));
    }

    @Override
    public String toString()
    {
        return "(postId='" + postId + "\', " +
                "author='" + author + "\', " +
                "message='" + message + "\', " +
                "created='" + created + "\', " +
                "links=" + links + ")";
    }
}