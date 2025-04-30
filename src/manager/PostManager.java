package manager;

import models.user.*;

public class PostManager {
    public boolean addPost(User user , String caption, String imagePath)
    {
        if(user.getProfile().countPosts() >= user.getUserplan().getMaxPosts())
        {
            System.out.println("You Reached Max amount of Posts");
            return false ;
        }
        Post post = new Post(caption, imagePath);
        user.getProfile().addPost(post);
        return true ;
    }

    public boolean removePost(User user , Post post)
    {
        if(user.getProfile().getPosts().contains(post))
        {
            user.getProfile().removePost(post);
            return true ;
        }
        return false ;
    }

    public void showUserPosts(User user) {

        for (Post post : user.getProfile().getPosts()) {
            System.out.println(post);
        
    }
}
}