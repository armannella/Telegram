package models.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String username)
    {
        super("User '" + username + "' already exists.");
    }

}
