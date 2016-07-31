package com.example.SampleAPI.services;

import com.example.SampleAPI.exceptions.BadRequestException;
import com.example.SampleAPI.exceptions.DatabaseException;
import com.example.SampleAPI.exceptions.NotFoundException;
import com.example.SampleAPI.models.Post;

import java.sql.*;
import java.util.ArrayList;

import static com.example.SampleAPI.services.UserService.PROPERTY_PROFILE_NAME;
import static com.example.SampleAPI.services.UserService.TABLE_USER;
import static com.example.SampleAPI.services.Utility.close;

public class PostService
{
    private final static String TABLE_POST = "post";
    private final static String PROPERTY_POST_ID = "postId";
    private final static String PROPERTY_AUTHOR = "author";
    private final static String PROPERTY_MESSAGE = "message";
    private final static String PROPERTY_CREATED = "created";

    private final static String GET_POST_FOR_USER = "SELECT * FROM " + TABLE_POST + ", " + TABLE_USER + " WHERE " + PROPERTY_AUTHOR + " = " + PROPERTY_PROFILE_NAME +" AND " + PROPERTY_PROFILE_NAME + " = ? AND " + PROPERTY_POST_ID + " = ?;";
    private final static String GET_ALL_POSTS_FOR_USER = "SELECT * FROM " + TABLE_POST + ", " + TABLE_USER + " WHERE " + PROPERTY_AUTHOR + " = " + PROPERTY_PROFILE_NAME + " AND " + PROPERTY_PROFILE_NAME + " = ?;";
    private final static String ADD_POST_FOR_USER = "INSERT INTO " + TABLE_POST + " VALUES (?, ?, ?, ?);";
    private final static String UPDATE_POST_MESSAGE_FOR_USER = "UPDATE " + TABLE_POST + " SET " + PROPERTY_MESSAGE + " = ? WHERE " + PROPERTY_AUTHOR + " = ? AND " + PROPERTY_POST_ID + " = ?;";
    private final static String REMOVE_POST_FOR_USER = "DELETE FROM " + TABLE_POST + " WHERE " + PROPERTY_AUTHOR + " = ? AND " + PROPERTY_POST_ID + " = ?;";
    private final static String REMOVE_ALL_POSTS_FOR_USER = "DELETE FROM " + TABLE_POST + " WHERE " + PROPERTY_AUTHOR + " = ?;";

    public static synchronized Post getPostForUser(String authorParam, String postIdParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            if(authorParam == null || authorParam.isEmpty() || postIdParam == null || postIdParam.isEmpty())
            {
                throw new BadRequestException("either author or post id is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(GET_POST_FOR_USER);
            preparedStatement.setString(1, authorParam);
            preparedStatement.setString(2, postIdParam);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
            {
                throw new NotFoundException("no post with such id and author is found.");
            }

            return new Post(
                    resultSet.getString(PROPERTY_POST_ID),
                    resultSet.getString(PROPERTY_AUTHOR),
                    resultSet.getString(PROPERTY_MESSAGE),
                    resultSet.getDate(PROPERTY_CREATED)
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

    public static synchronized ArrayList<Post> getAllPostsForUser(String authorParam) throws BadRequestException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            if(authorParam == null || authorParam.isEmpty())
            {
                throw new BadRequestException("author is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(GET_ALL_POSTS_FOR_USER);
            preparedStatement.setString(1, authorParam);
            resultSet = preparedStatement.executeQuery();

            ArrayList<Post> posts = new ArrayList<>();
            while(resultSet.next())
            {
                posts.add(
                        new Post(
                                resultSet.getString(PROPERTY_POST_ID),
                                resultSet.getString(PROPERTY_AUTHOR),
                                resultSet.getString(PROPERTY_MESSAGE),
                                resultSet.getDate(PROPERTY_CREATED)
                        )
                );
            }

            return posts;
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

    public static synchronized Post addPostForUser(Post postParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(postParam == null ||
                    postParam.getAuthor() == null || postParam.getAuthor().isEmpty() ||
                    postParam.getMessage() == null || postParam.getMessage().isEmpty())
            {
                throw new BadRequestException("either post or author or message is not valid.");
            }

            Post newPost = new Post(postParam.getAuthor(), postParam.getMessage());

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(ADD_POST_FOR_USER);

            preparedStatement.setString(1, newPost.getPostId());
            preparedStatement.setString(2, newPost.getAuthor());
            preparedStatement.setString(3, newPost.getMessage());
            preparedStatement.setDate(4, newPost.getCreated());
            preparedStatement.executeUpdate();

            return getPostForUser(newPost.getAuthor(), newPost.getPostId());
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            throw new NotFoundException("no author with such profile name is found.");
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

    public static synchronized Post updatePostForUser(Post postParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(postParam == null ||
                    postParam.getAuthor() == null || postParam.getAuthor().isEmpty() ||
                    postParam.getPostId() == null || postParam.getPostId().isEmpty() ||
                    postParam.getMessage() == null)
            {
                throw new BadRequestException("either post or author or message is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            if(postParam.getMessage() != null)
            {
                preparedStatement = connection.prepareStatement(UPDATE_POST_MESSAGE_FOR_USER);
                preparedStatement.setString(1, postParam.getMessage());
                preparedStatement.setString(2, postParam.getAuthor());
                preparedStatement.setString(3, postParam.getPostId());
                int result = preparedStatement.executeUpdate();

                if(result == 0)
                {
                    throw new NotFoundException("no post with such id and author is found.");
                }
            }

            return getPostForUser(postParam.getAuthor(), postParam.getPostId());
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

    public static synchronized void removePostForUser(String authorParam, String postIdParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(authorParam == null || authorParam.isEmpty() || postIdParam == null || postIdParam.isEmpty())
            {
                throw new BadRequestException("either author or post id is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(REMOVE_POST_FOR_USER);
            preparedStatement.setString(1, authorParam);
            preparedStatement.setString(2, postIdParam);
            int result = preparedStatement.executeUpdate();

            if(result == 0)
            {
                throw new NotFoundException("no post with such id and author is found.");
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

    public static synchronized void removeAllPostsForUser(String authorParam) throws BadRequestException, NotFoundException, DatabaseException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try
        {
            if(authorParam == null || authorParam.isEmpty())
            {
                throw new BadRequestException("author is not valid.");
            }

            connection = DriverManager.getConnection(Utility.CONNECTION);
            preparedStatement = connection.prepareStatement(REMOVE_ALL_POSTS_FOR_USER);
            preparedStatement.setString(1, authorParam);
            int result = preparedStatement.executeUpdate();

            if(result == 0)
            {
                throw new NotFoundException("no post with such author is found.");
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