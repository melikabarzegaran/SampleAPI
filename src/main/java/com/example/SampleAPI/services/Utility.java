package com.example.SampleAPI.services;

import com.example.SampleAPI.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Utility
{
    private final static String APPLICATION_NAME = "SampleAPI";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    final static String CONNECTION =
            "jdbc:mysql://localhost/" + APPLICATION_NAME
            + "?user=" + USERNAME
            + "&password=" + PASSWORD
            + "&useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";

    static void close(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
    {
        try
        {
            if(resultSet != null)
            {
                resultSet.close();
            }
            if(preparedStatement != null)
            {
                preparedStatement.close();
            }
            if(connection != null)
            {
                connection.close();
            }
        }
        catch (Exception e)
        {
            throw new DatabaseException();
        }
    }

    static void printBorder()
    {
        System.out.println("----------------------------------------------------------------------------------------------------");
    }
}