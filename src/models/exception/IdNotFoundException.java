package models.exception;

public class IdNotFoundException extends Exception {
    public IdNotFoundException(int id)
    {
        super("There is not anything with ID : " + id);
    }
}
