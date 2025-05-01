package models.message;

import models.user.User;
import util.DateTimeUtil;

public class InfoMessage extends Message{
    String Info ;

    public InfoMessage(User user1 , ActionType action , User user2)
    {
        super(user1);
        Info = user1.getUsername() + " " + action.getText() + " " + user2.getUsername() + " (" + DateTimeUtil.format(timestamp)+ ")";
    }

    public InfoMessage(User user , ActionType action)
    {
        super(user);
        Info = user.getUsername() + " " + action.getText()+ " (" + DateTimeUtil.format(timestamp)+ ")";
    }

    @Override
    public void display()
    {
        System.out.println("[INFO] " + Info);
    }
    
}
