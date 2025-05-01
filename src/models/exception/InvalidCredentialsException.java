package models.exception;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String username)
    {
        super("Invalid password for user : " + username);
    }

}
