package models.exception;

public class NoPermissionException extends Exception {
    public NoPermissionException(String action)
    {
        super("You dont have permission to " + action);
    }

}
