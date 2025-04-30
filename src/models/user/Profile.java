package models.user;

import java.util.ArrayList;

public class Profile {

    private String bio ;
    private ArrayList<Post> posts = new ArrayList<>();

    public void setBio(String bio)
    {
        this.bio = bio ;
    }

    public String getBio () {
        return this.bio ;
    }

    public void addPost (Post post)
    {
        posts.add(post);
    }
    
    public void removePost(Post post)
    {
        posts.remove(post);
    }

    public ArrayList<Post> getPosts()
    {
        return this.posts;
    } 

    public int countPosts()
    {
        return posts.size();
    }

    public Post getPostbyID (int id)
    {
        for (Post post : posts)
        {
            if(post.getID() == id)
            {
                return post ;
            }
        }
        return null ;
    }
}
