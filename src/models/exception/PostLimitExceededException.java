package models.exception;

public class PostLimitExceededException extends Exception{
    public PostLimitExceededException()
    {
        super("You reached Maximum Limit of Post . Upgrade your role ");
    }

}
