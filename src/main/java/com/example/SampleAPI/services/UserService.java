package com.example.SampleAPI.services;

import com.example.SampleAPI.exceptions.BadRequestException;
import com.example.SampleAPI.exceptions.ConflictException;
import com.example.SampleAPI.exceptions.DatabaseException;
import com.example.SampleAPI.exceptions.NotFoundException;
import com.example.SampleAPI.models.User;

import java.sql.*;
import java.util.ArrayList;

import static com.example.SampleAPI.services.Utility.close;

public class UserService
{
    final static String TABLE_USER = "user";
    final static String PROPERTY_PROFILE_NAME = "profileName";
    private final static String PROPERTY_FIRST_NAME = "firstName";
    private final static String PROPERTY_LAST_NAME = "lastName";

    private final static String GET_USER = "SELECT * FROM " + TABLE_USER + " WHERE " + PROPERTY_PROFILE_NAME + " = ?;";
    private final static String GET_ALL_USERS = "SELECT * FROM " + TABLE_USER + ";";
    private final static String ADD_USER = "INSERT INTO " + TABLE_USER + " VALUES(?, ?, ?);";
    private final static String UPDATE_USER_FIRST_NAME = "UPDATE " + TABLE_USER + " SET " + PROPERTY_FIRST_NAME + " = ? WHERE " + PROPERTY_PROFILE_NAME + " = ?;";
    private final static String UPDATE_USER_LAST_NAME = "UPDATE " + TABLE_USER + " SET " + PROPERTY_LAST_NAME + " = ? WHERE " + PROPERTY_PROFILE_NAME + " = ?;";
    private final static String REMOVE_USER = "DELETE FROM " + TABLE_USER + " WHERE " + PROPERTY_PROFILE_NAME + " = ?;";

    public static synchronized User getUser(String profileNameParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            if(profileNameParam == null || profileNameParam.isEmpty())
            {
                throw new BadRequestException("input is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(GET_USER);
            preparedStatement.setString(1, profileNameParam);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
            {
                throw new NotFoundException("no user with such profile name is found.");
            }

            return new User(
                    resultSet.getString(PROPERTY_PROFILE_NAME),
                    resultSet.getString(PROPERTY_FIRST_NAME),
                    resultSet.getString(PROPERTY_LAST_NAME)
            );
        }
        catch(SQLException e)
        {
            throw new DatabaseException(e);
        }
        finally
        {
            close(connection, preparedStatement, resultSet);
        }
    }

    public static synchronized ArrayList<User> getAllUsers() throws DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            resultSet = preparedStatement.executeQuery();

            ArrayList<User> users = new ArrayList<>();
            while(resultSet.next())
            {
                users.add(
                        new User(
                                resultSet.getString(PROPERTY_PROFILE_NAME),
                                resultSet.getString(PROPERTY_FIRST_NAME),
                                resultSet.getString(PROPERTY_LAST_NAME)
                        )
                );
            }

            return users;
        }
        catch(SQLException e)
        {
            throw new DatabaseException(e);
        }
        finally
        {
            close(connection, preparedStatement, resultSet);
        }
    }

    public static synchronized User addUser(User userParam) throws BadRequestException, ConflictException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(userParam == null || userParam.getProfileName() == null || userParam.getProfileName().isEmpty())
            {
                throw new BadRequestException("input is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, userParam.getProfileName());
            preparedStatement.setString(2, (userParam.getFirstName() == null || userParam.getFirstName().isEmpty()) ? null : userParam.getFirstName());
            preparedStatement.setString(3, (userParam.getLastName() == null || userParam.getLastName().isEmpty()) ? null : userParam.getLastName());
            preparedStatement.executeUpdate();

            return getUser(userParam.getProfileName());
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            throw new ConflictException("profile name is already taken.");
        }
        catch(SQLException e)
        {
            throw new DatabaseException(e);
        }
        finally
        {
            close(connection, preparedStatement, null);
        }
    }

    public static synchronized User updateUser(User userParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(userParam == null || userParam.getProfileName() == null || userParam.getProfileName().isEmpty())
            {
                throw new BadRequestException("profile name is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            if(userParam.getFirstName() != null)
            {
                preparedStatement = connection.prepareStatement(UPDATE_USER_FIRST_NAME);
                preparedStatement.setString(1, userParam.getFirstName());
                preparedStatement.setString(2, userParam.getProfileName());
                int result = preparedStatement.executeUpdate();

                if(result == 0)
                {
                    throw new NotFoundException("no user with such profile name is found.");
                }
            }
            if(userParam.getLastName() != null)
            {
                preparedStatement = connection.prepareStatement(UPDATE_USER_LAST_NAME);
                preparedStatement.setString(1, userParam.getLastName());
                preparedStatement.setString(2, userParam.getProfileName());
                int result = preparedStatement.executeUpdate();

                if(result == 0)
                {
                    throw new NotFoundException("no user with such profile name is found.");
                }
            }

            return getUser(userParam.getProfileName());
        }
        catch(SQLException e)
        {
            throw new DatabaseException(e);
        }
        finally
        {
            close(connection, preparedStatement, null);
        }
    }

    public static synchronized void removeUser(String profileNameParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(profileNameParam == null || profileNameParam.isEmpty())
            {
                throw new BadRequestException("input is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(REMOVE_USER);
            preparedStatement.setString(1, profileNameParam);
            int result = preparedStatement.executeUpdate();

            if(result == 0)
            {
                throw new NotFoundException("no user with such profile name is found.");
            }
        }
        catch(SQLException e)
        {
            throw new DatabaseException(e);
        }
        finally
        {
            close(connection, preparedStatement, null);
        }
    }
}