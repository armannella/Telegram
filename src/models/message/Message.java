package models.message;
import java.time.LocalDateTime;
import models.user.*;

public abstract class Message {
    protected  User sender ;
    protected LocalDateTime timestamp ;
    protected static int counter = 1 ;
    protected int id ;
    
    public Message(User sender)
    {
        this.id = counter++;
        this.sender = sender ;
        this.timestamp = LocalDateTime.now();
    }

    public abstract void display();

    public User getSender()
    {
        return this.sender;
    }

    public LocalDateTime getTimestamp()
    {
        return this.timestamp;
    }
    
    public int getID()
    {
        return this.id ;
    }
}
