package manager;

import java.util.ArrayList;
import models.exception.InvalidCredentialsException;
import models.exception.UserAlreadyExistsException;
import models.exception.UserNotFoundException;
import models.user.User;

public class UserManager {

    private ArrayList<User> users ;

    public UserManager()
    {
        users = new ArrayList<>() ;
    }

    public void register(String username , String password , String nickname , String phonenumber) throws UserAlreadyExistsException
    {
        if(userExists(username) || phoneNumberExists(phonenumber))
        {
            throw new UserAlreadyExistsException(username);
        }   
        users.add(new User(username, password, nickname, phonenumber));
    }

    public User login (String username , String password) throws UserNotFoundException,InvalidCredentialsException
    {
        User user = findUserByUsername(username);
        if (!user.checkPassword(password)) 
        {
            throw new InvalidCredentialsException(username);
        }
        return user ;
    }

    public User findUserByUsername(String username) throws UserNotFoundException
    {
        for (User user : users)
        {
            if ( user.getUsername().equals(username))
                return user ;
        }
        throw new UserNotFoundException();
    }

    public boolean userExists(String username)
    {
        for (User user : users)
        {
            if ( user.getUsername().equals(username))
                return true ;
        }
        return false ;
    }
    
    public boolean phoneNumberExists(String phonenumber)
    {
        for (User user : users)
        {
            if ( user.getPhonenumber().equals(phonenumber))
                return true ;
        }
        return false ;
    }

    public ArrayList<User> getAllUsers()
    {
        return users ;
    }
}
