package com.example.SampleAPI.services;

import com.example.SampleAPI.models.User;

import java.util.ArrayList;

import static com.example.SampleAPI.services.Utility.printBorder;

public class UserServiceTest
{
    private static void getAllUsers()
    {
        try
        {
            printBorder();
            System.out.println("get all users:");

            ArrayList<User> users = UserService.getAllUsers();
            for(User user: users)
            {
                System.out.println(user);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            printBorder();
        }
    }

    private static void getUser(String profileName)
    {
        try
        {
            printBorder();
            System.out.println("get user: (profile name='" + profileName + "')");

            User user = UserService.getUser(profileName);
            System.out.println(user);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            printBorder();
        }
    }

    private static void getUser(String nonExistingProfileName, String existingProfileName)
    {
        getUser(null);
        getUser("");
        getUser(nonExistingProfileName);
        getUser(existingProfileName);
    }

    private static void addUser(User user)
    {
        try
        {
            printBorder();
            if(user == null)
            {
                System.out.println("add user:");
            }
            else
            {
                System.out.println("add user: " +
                        "(profile name='" + user.getProfileName() + "', " +
                        "first name='" + user.getFirstName() + "', " +
                        "last name='" + user.getLastName() + "')");
            }

            User newUser = UserService.addUser(user);
            System.out.println(newUser);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            printBorder();
        }
    }

    private static void addUser(String existingProfileName, String nonExistingProfileName)
    {
        addUser(null);

        String[] profileNames = new String[]{null, "", existingProfileName, nonExistingProfileName};
        String[] firstNames = new String[]{null, "", "firstname"};
        String[] lastNames = new String[]{null, "", "lastname"};

        for(String profileName : profileNames)
        {
            for(String firstName : firstNames)
            {
                for(String lastName : lastNames)
                {
                    addUser(new User(profileName, firstName, lastName));
                }
            }
        }
    }

    private static void updateUser(User user)
    {
        try
        {
            printBorder();

            if(user == null)
            {
                System.out.println("update user:");
            }
            else
            {
                System.out.println("update user: " +
                        "(profile name='" + user.getProfileName() + "', " +
                        "first name='" + user.getFirstName() + "', " +
                        "last name='" + user.getLastName() + "')");
            }

            User newUser = UserService.updateUser(user);
            System.out.println(newUser);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            printBorder();
        }
    }

    private static void updateUser(String nonExistingProfileName, String existingProfileName)
    {
        updateUser(null);

        String[] profileNames = new String[]{null, "", nonExistingProfileName, existingProfileName};
        String[] firstNames = new String[]{null, "", "firstname"};
        String[] lastNames = new String[]{null, "", "lastname"};

        for(String profileName : profileNames)
        {
            for(String firstName : firstNames)
            {
                for(String lastName : lastNames)
                {
                    updateUser(new User(profileName, firstName, lastName));
                }
            }
        }
    }

    private static void removeUser(String profileName)
    {
        try
        {
            printBorder();
            System.out.println("remove user: (profile name='" + profileName + "')");

            UserService.removeUser(profileName);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            printBorder();
        }
    }

    private static void removeUser(String nonExistingProfileName, String existingProfileName)
    {
        removeUser(null);
        removeUser("");
        removeUser(nonExistingProfileName);
        removeUser(existingProfileName);
    }

    public static void main(String args[])
    {
        getAllUsers();
        getUser("profile", "bgordonj");
        addUser("bgordonj", "profile");
        updateUser("another-profile", "bgordonj");
        removeUser("another-profile", "bgordonj");
    }
}