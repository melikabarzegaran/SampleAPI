package com.example.SampleAPI.exceptions;

import com.example.SampleAPI.representations.ErrorRepresentation;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

class Utility
{
    static Response createResponse(Throwable e, Response.Status status)
    {
        ErrorRepresentation error =
                new ErrorRepresentation(
                        e.getClass().getSimpleName(),
                        status.getStatusCode(),
                        e.getMessage()
                );

        return Response
                .status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}