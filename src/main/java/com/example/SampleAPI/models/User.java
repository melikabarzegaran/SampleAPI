package com.example.SampleAPI.models;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

@XmlType(propOrder = {"profileName", "firstName", "lastName", "links"})
public class User
{
    private String profileName;
    private String firstName;
    private String lastName;
    private ArrayList<Link> links;

    public User()
    {
        this(null, null, null);
    }

    public User(String profileName, String firstName, String lastName)
    {
        this(profileName, firstName, lastName, new ArrayList<Link>());
    }

    public User(String profileName, String firstName, String lastName, ArrayList<Link> links)
    {
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.links = links;
    }

    public String getProfileName()
    {
        return profileName;
    }

    public void setProfileName(String profileName)
    {
        this.profileName = profileName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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

    public void addSelfAndPosts(String selfUri, String postsUri)
    {
        links.add(new Link("self", selfUri));
        links.add(new Link("posts", postsUri));
    }

    @Override
    public String toString()
    {
        return "(profileName='" + profileName + "\', " +
                "firstName='" + firstName + "\', " +
                "lastName='" + lastName + "\', " +
                "links=" + links + ")";
    }
}