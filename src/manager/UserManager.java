package manager;

import java.util.ArrayList;
import models.user.User;

public class UserManager {

    private ArrayList<User> users ;

    public UserManager()
    {
        users = new ArrayList<>() ;
    }

    public void register(String username , String password , String nickname , String phonenumber)
    {
        users.add(new User(username, password, nickname, phonenumber));
    }

    public User login (String username , String password)
    {
        User user = findUserByUsername(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public User findUserByUsername(String username)
    {
        for (User user : users)
        {
            if ( user.getUsername().equals(username))
                return user ;
        }
        return null;

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
