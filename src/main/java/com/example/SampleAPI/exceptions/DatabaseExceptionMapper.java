package com.example.SampleAPI.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatabaseExceptionMapper implements ExceptionMapper<DatabaseException>
{
    @Override
    public Response toResponse(DatabaseException e)
    {
        return Utility.createResponse(e, Response.Status.CONFLICT);
    }
}