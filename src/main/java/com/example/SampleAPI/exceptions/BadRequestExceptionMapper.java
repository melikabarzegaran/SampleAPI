package com.example.SampleAPI.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException>
{
    @Override
    public Response toResponse(BadRequestException e)
    {
        return Utility.createResponse(e, Response.Status.BAD_REQUEST);
    }
}