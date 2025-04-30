package models.chat;

import models.user.*;

public class ChatMember {
    private User user ;
    private Role role ;

    public ChatMember (User user , Role role)
    {
        this.role = role ;
        this.user = user ;
    }
    public User getUser()
    {
        return user ;
    }

    public Role getRole()
    {
        return role ;
    }

    public void setRole(Role role)
    {
        this.role = role ;
    }

    public void showMembers()
    {
        System.out.println("- " + user.getUsername() + " ---> " + role.getRoleName());
    }



}
