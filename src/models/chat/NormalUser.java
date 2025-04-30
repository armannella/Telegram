package models.chat;

import models.message.Message;
import models.user.User;

public class NormalUser implements Role{

    @Override
    public boolean canSendMessage()
    {
        return true ;
    }
    
    @Override
    public boolean canEditMessage(Message message, User editor)
    {
        return message.getSender().equals(editor) ;
    }

    @Override
    public boolean canDeleteMessage(Message message, User deleter)
    {
        return message.getSender().equals(deleter) ;
    }

    @Override
    public boolean canManageAdmins()
    {
        return false ;
    }

    @Override
    public String getRoleName()
    {
        return "NormalUser" ;
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
