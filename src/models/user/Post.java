package models.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import models.Identifiable;
import util.DateTimeUtil;

public class Post implements Identifiable{
    private static int counter = 1;
    private int id ;
    private String caption ;
    private String imagePath ;
    private ArrayList<User> likes = new ArrayList<>();
    private final LocalDateTime timestamp ;

    public Post(String caption, String imagePath)
    {
        this.caption = caption;
        this.imagePath = imagePath;
        this.timestamp = LocalDateTime.now();
        this.id = counter++;
    }


    public void setCaption (String caption)
    {
        this.caption = caption ;
    }

    public String getCaption()
    {
        return this.caption ;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void addLike(User user)
    {
        if(!likes.contains(user))
        {
            this.likes.add(user);
        }
        else
        {
            removeLike(user);
        }  
    }

    public void removeLike(User user)
    {
        if(likes.contains(user))
        {
            this.likes.remove(user);
        }  
    }

    public int likesCount()
    {
        return likes.size();
    }

    public ArrayList<User> getLikes() {
        return likes ;
    }

    @Override
    public int getID()
    {
        return this.id;
    }

    public void showLikes() {
        for (User user : likes) {
            System.out.println(user.getUsername());
        }
    }

    @Override
    public String toString() {
        return "ID : " + id + " | " + imagePath + " | " + caption + " (" + DateTimeUtil.format(timestamp) + ") | Like Counts = " + likesCount();
    }
}


