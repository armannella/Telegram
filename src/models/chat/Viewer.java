package models.chat;

import models.message.Message;
import models.user.User;

public class Viewer implements Role{

    @Override
    public boolean canSendMessage()
    {
        return false ;
    }
    
    @Override
    public boolean canEditMessage(Message message, User editor)
    {
        return false ;
    }

    @Override
    public boolean canDeleteMessage(Message message, User deleter)
    {
        return false ;
    }

    @Override
    public boolean canManageAdmins()
    {
        return false ;
    }

    @Override
    public String getRoleName()
    {
        return "Viewer" ;
    }


    @Override
    public boolean canAddMember()
    {
        return true;
    }

    @Override
    public boolean canDeleteMember()
    {
        return false;
    }

    @Override
    public boolean canChangeTopic()
    {
        return false ;
    }
}
