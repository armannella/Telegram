package models.exception;

public class NotEditableMessageException extends Exception{
    public NotEditableMessageException(String type)
    {
        super("This kind of messages are not Editable : "+ type);
    }

}
