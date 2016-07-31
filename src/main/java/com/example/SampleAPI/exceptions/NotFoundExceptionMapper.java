package com.example.SampleAPI.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>
{
    @Override
    public Response toResponse(NotFoundException e)
    {
        return Utility.createResponse(e, Response.Status.NOT_FOUND);
    }
}