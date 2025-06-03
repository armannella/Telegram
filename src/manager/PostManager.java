package manager;

import models.exception.PostLimitExceededException;
import models.user.*;

public class PostManager {
    
    public void addPost(User user , String caption, String imagePath) throws PostLimitExceededException
    {
        if(user.getProfile().countPosts() >= user.getUserplan().getMaxPosts())
        {
            throw new PostLimitExceededException();
        }

        Post post = new Post(caption, imagePath);
        user.getProfile().addPost(post);
    }

    public void removePost(User user , Post post)
    {
            user.getProfile().removePost(post);
    }

    public void showUserPosts(User user) {

        for (Post post : user.getProfile().getPosts()) {
            System.out.println(post);
        
    }
}
}