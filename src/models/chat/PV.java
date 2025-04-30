package models.chat;

import models.user.User;

public class PV extends Chat{

    public PV(ChatMember one , ChatMember two)
    {
        super();
        super.addMember(one);
        super.addMember(two);
        super.setTitle(one.getUser().getNickname() + " - " + two.getUser().getNickname());
    }

    @Override
    public boolean addMember(ChatMember chatMember)
    {
        if(countMembers() == 2)
        {
            System.out.println("This is PV chat can not add new member");
            return false;
        }
        else{
            super.addMember(chatMember);
            return true ;
        }
    }

    @Override
    public boolean removeMember(User user)
    {
        System.out.println("this is pv chat u can not delete member");
        return false ;
    }


    @Override
    public boolean promoteToAdmin(User user)
    {
        System.out.println("this is PV chat !! we dont have admin");
        return false;
    }

    @Override
    public boolean demoteFromAdmin(User user , Role role)
    {
        System.out.println("this is PV chat !! we dont have admin");
        return false;
    }

    
    @Override
    public String getType()
    {
        return "PV" ;
    }


    
}
