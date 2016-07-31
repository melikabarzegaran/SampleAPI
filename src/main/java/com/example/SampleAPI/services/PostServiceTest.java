package com.example.SampleAPI.services;

import com.example.SampleAPI.models.Post;

import java.util.ArrayList;

import static com.example.SampleAPI.services.Utility.printBorder;

public class PostServiceTest
{
    private static void getAllPostsForUser(String author)
    {
        try
        {
            printBorder();
            System.out.println("get all posts for user: (author='" + author + "')");

            ArrayList<Post> posts = PostService.getAllPostsForUser(author);
            for(Post post: posts)
            {
                System.out.println(post);
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

    private static void getAllPostsForUser(String nonExistingAuthor, String existingAuthor)
    {
        getAllPostsForUser(null);
        getAllPostsForUser("");
        getAllPostsForUser(nonExistingAuthor);
        getAllPostsForUser(existingAuthor);
    }

    private static void getPostForUser(String author, String postId)
    {
        try
        {
            printBorder();
            System.out.println("get post for user: " +
                    "(author='" + author + "', " +
                    "post id='" + postId + "'')");

            Post post = PostService.getPostForUser(author, postId);
            System.out.println(post);
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

    private static void getPostForUser(
            String nonExistingAuthor, String existingAuthor,
            String nonExistingPostId, String existingIrrelevantPostId, String existingRelevantPostId
    )
    {
        String[] authors = new String[]{null, "", nonExistingAuthor, existingAuthor};
        String[] postIds = new String[]{null, "", nonExistingPostId, existingIrrelevantPostId, existingRelevantPostId};

        for(String author : authors)
        {
            for(String postId : postIds)
            {
                getPostForUser(author, postId);
            }
        }
    }

    private static void addPostForUser(Post post)
    {
        try
        {
            printBorder();
            if(post == null)
            {
                System.out.println("add post for user:");
            }
            else
            {
                System.out.println("add post for user: " +
                        "(author='" + post.getAuthor() + "', " +
                        "message='" + post.getMessage() + "')");
            }

            Post newPost = PostService.addPostForUser(post);
            System.out.println(newPost);
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

    private static void addPostForUser(String nonExistingAuthor, String existingAuthor)
    {
        addPostForUser(null);

        String[] authors = new String[]{null, "", nonExistingAuthor, existingAuthor};
        String[] messages = new String[]{null, "", "message"};

        for(String author : authors)
        {
            for(String message : messages)
            {
                addPostForUser(new Post(author, message));
            }
        }
    }

    private static void updatePostForUser(Post post)
    {
        try
        {
            printBorder();
            if(post == null)
            {
                System.out.println("update post for user:");
            }
            else
            {
                System.out.println("update post for user: " +
                        "(author='" + post.getAuthor() + "', " +
                        "post id='" + post.getPostId() + "', " +
                        "message='" + post.getMessage() + "')");
            }

            Post newPost = PostService.updatePostForUser(post);
            System.out.println(newPost);
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

    private static void updatePostForUser(
            String nonExistingAuthor, String existingAuthor,
            String nonExistingPostId, String existingIrrelevantPostId, String existingRelevantPostId
    )
    {
        updatePostForUser(null);

        String[] authors = new String[]{null, "", nonExistingAuthor, existingAuthor};
        String[] postIds = new String[]{null, "", nonExistingPostId, existingIrrelevantPostId, existingRelevantPostId};
        String[] messages = new String[]{null, "", "message"};

        for(String author : authors)
        {
            for(String postId : postIds)
            {
                for(String message : messages)
                {
                    updatePostForUser(new Post(postId, author, message));
                }
            }
        }
    }

    private static void removePostForUser(String author, String postId)
    {
        try
        {
            printBorder();
            System.out.println("remove post for user: " +
                    "(author='" + author + "', " +
                    "post id='" + postId + "'')");

            PostService.removePostForUser(author, postId);
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

    private static void removePostForUser(
            String nonExistingAuthor, String existingAuthor,
            String nonExistingPostId, String existingIrrelevantPostId, String existingRelevantPostId
    )
    {
        String[] authors = new String[]{null, "", nonExistingAuthor, existingAuthor};
        String[] postIds = new String[]{null, "", nonExistingPostId, existingIrrelevantPostId, existingRelevantPostId};

        for(String author : authors)
        {
            for(String postId : postIds)
            {
                removePostForUser(author, postId);
            }
        }
    }

    private static void removeAllPostsForUser(String author)
    {
        try
        {
            printBorder();
            System.out.println("remove all posts for user: (author='" + author + "')");

            PostService.removeAllPostsForUser(author);
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

    private static void removeAllPostsForUser(String nonExistingAuthor, String existingAuthor)
    {
        removeAllPostsForUser(null);
        removeAllPostsForUser("");
        removeAllPostsForUser(nonExistingAuthor);
        removeAllPostsForUser(existingAuthor);
    }

    public static void main(String args[])
    {
        getAllPostsForUser("profile", "bgordonj");
        getPostForUser("profile", "bgordonj", "e769f6e4-4707-4859-b57e-bb9928bc7d59", "e769f6e4-4707-4859-b57e-bb9928bc7d58", "1c2010cf-daaa-429f-8916-80c1fb6446e2");
        addPostForUser("profile", "bgordonj");
        updatePostForUser("another-profile", "bgordonj", "e769f6e4-4707-4859-b57e-bb9928bc7d59", "e769f6e4-4707-4859-b57e-bb9928bc7d58", "1c2010cf-daaa-429f-8916-80c1fb6446e2");
        removePostForUser("another-profile", "bgordonj", "e769f6e4-4707-4859-b57e-bb9928bc7d59", "e769f6e4-4707-4859-b57e-bb9928bc7d58", "1c2010cf-daaa-429f-8916-80c1fb6446e2");
        removeAllPostsForUser("profile", "bgordonj");
    }
}