package models.exception;

public class UserAlreadyInChatException extends Exception{
    public UserAlreadyInChatException()
    {
        super("This User is Already in this Chat");
    }
}
