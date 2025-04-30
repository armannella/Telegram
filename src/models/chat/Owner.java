package models.chat;

import models.message.Message;
import models.user.User;

public class Owner implements Role{

    @Override
    public boolean canSendMessage()
    {
        return true ;
    }
    
    @Override
    public boolean canEditMessage(Message message, User editor)
    {
        return true ;
    }

    @Override
    public boolean canDeleteMessage(Message message, User deleter)
    {
        return true ;
    }

    @Override
    public boolean canManageAdmins()
    {
        return true ;
    }

    @Override
    public String getRoleName()
    {
        return "Owner" ;
    }

    @Override
    public boolean canAddMember()
    {
        return true;
    }

    @Override
    public boolean canDeleteMember()
    {
        return true;
    }

    @Override
    public boolean canChangeTopic()
    {
        return true ;
    }
}
