package com.example.SampleAPI.resources;

import com.example.SampleAPI.models.Post;
import com.example.SampleAPI.representations.PostRepresentation;
import com.example.SampleAPI.representations.PostsRepresentation;
import com.example.SampleAPI.services.PostService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.ArrayList;

import static com.example.SampleAPI.resources.PostResource.POST_COLLECTION_RESOURCE_PATH;
import static com.example.SampleAPI.resources.UserResource.PROFILE_NAME_PATH_PARAM;

@Path(POST_COLLECTION_RESOURCE_PATH)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource
{
    public final static String POST_COLLECTION_RESOURCE_PATH = "/";
    public final static String POST_ITEM_RESOURCE_PATH = "/{postId}";
    public final static String POST_ID_PATH_PARAM = "postId";

    @GET
    @Path(POST_ITEM_RESOURCE_PATH)
    public Response getPost(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName, @PathParam(POST_ID_PATH_PARAM) String postId)
    {
        Post post = PostService.getPostForUser(profileName, postId);
        PostResource.addSelfAndAuthor(uriInfo, post);

        return Response.ok().entity(new PostRepresentation(post)).build();
    }

    @GET
    public Response getAllPosts(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName)
    {
        ArrayList<Post> posts = PostService.getAllPostsForUser(profileName);
        for(Post post : posts)
        {
            PostResource.addSelfAndAuthor(uriInfo, post);
        }

        return Response.ok().entity(new PostsRepresentation(posts)).build();
    }

    @POST
    public Response addPost(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName, PostRepresentation postRepresentation)
    {
        postRepresentation.getData().setAuthor(profileName);
        Post post = PostService.addPostForUser(postRepresentation.getData());
        PostResource.addSelfAndAuthor(uriInfo, post);

        return Response.created(getSelfUri(uriInfo, post.getAuthor(), post.getPostId())).entity(new PostRepresentation(post)).build();
    }

    @PUT
    @Path(POST_ITEM_RESOURCE_PATH)
    public Response updatePost(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName, @PathParam(POST_ID_PATH_PARAM) String postId, PostRepresentation postRepresentation)
    {
        postRepresentation.getData().setAuthor(profileName);
        postRepresentation.getData().setPostId(postId);
        Post post = PostService.updatePostForUser(postRepresentation.getData());
        PostResource.addSelfAndAuthor(uriInfo, post);

        return Response.ok().entity(new PostRepresentation(post)).build();
    }

    @DELETE
    @Path(POST_ITEM_RESOURCE_PATH)
    public Response removePost(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName, @PathParam(POST_ID_PATH_PARAM) String postId)
    {
        PostService.removePostForUser(profileName, postId);
        return Response.noContent().build();
    }

    @DELETE
    public Response removePosts(@Context UriInfo uriInfo, @PathParam(PROFILE_NAME_PATH_PARAM) String profileName)
    {
        PostService.removeAllPostsForUser(profileName);
        return Response.noContent().build();
    }

    private static Post addSelfAndAuthor(UriInfo uriInfo, Post post)
    {
        String selfUri = getSelfUri(uriInfo, post.getAuthor(), post.getPostId()).toString();
        String postsUri = getAuthorUri(uriInfo, post.getAuthor()).toString();
        post.addSelfAndAuthor(selfUri, postsUri);

        return post;
    }

    private static URI getSelfUri(UriInfo uriInfo, String profileName, String postId)
    {
        return uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(UserResource.class, "getPostResource")
                .path(postId)
                .resolveTemplate(PROFILE_NAME_PATH_PARAM, profileName)
                .resolveTemplate(POST_ID_PATH_PARAM, postId)
                .build();
    }

    private static URI getAuthorUri(UriInfo uriInfo, String profileName)
    {
        return uriInfo
                .getBaseUriBuilder()
                .path(UserResource.class)
                .path(profileName)
                .build();
    }
}