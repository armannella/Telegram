package models.exception;

public class UserNotInChat extends Exception{
    public UserNotInChat()
    {
        super("User is not In Chat");
    }

}
