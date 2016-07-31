package com.example.SampleAPI.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConflictExceptionMapper implements ExceptionMapper<ConflictException>
{
    @Override
    public Response toResponse(ConflictException e)
    {
        return Utility.createResponse(e, Response.Status.CONFLICT);
    }
}