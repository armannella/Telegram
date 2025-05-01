package models.chat;

import java.util.ArrayList;
import manager.Finder;
import models.Identifiable;
import models.exception.IdNotFoundException;
import models.message.Message;
import models.user.User;

public abstract class Chat implements Identifiable{
    private static int counter = 1 ;
    private int id ;
    private ArrayList<ChatMember> members = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private String Title ;

    public Chat()
    {
        this.id = counter ;
        counter++ ;
    }

    public void addMember(ChatMember user) 
    {
        members.add(user);
    }

    public void removeMember(User user)
    {
        for (ChatMember member : members)
        {
            if(member.getUser().equals(user))
            {
                members.remove(member);
            }
        }
    }
    
    public ArrayList<ChatMember> getMembers()
    {
        return this.members;
    }

    public int countMembers()
    {
        return members.size();
    }

    public ArrayList<Message> getMessages()
    {
        return this.messages ;
    }

    public int countMessages()
    {
        return messages.size();
    }

    public void addMessage(Message message)
    {
        this.messages.add(message);
    }

    public void deleteMessage(Message message)
    {
        messages.remove(message);
    }

    @Override
    public int getID()
    {
        return this.id;
    }

    public boolean isUserinChat(User user)
    {
        for (ChatMember chatMember : members)
        {
            if (chatMember.getUser().equals(user))
            {
                return true ;
            }
        }
        return false ;
    }

    public Message findMessageById(int id) throws IdNotFoundException {

        Finder<Message> finder = new Finder<>();
        return finder.findByID(messages, id);
        
    }

    public Role getRoleofUser(User user) {
        for (ChatMember member : members) {
            if (member.getUser().equals(user)) {
                return member.getRole();
            }
        }
        return null;
    }

    public void setTitle(String title)
    {
        this.Title = title ;
    }
    
    public String  getTitle()
    {
        return this.Title;
    }
    
    public void changeRoleOfUser(User user , Role role)
    {
        for (ChatMember member : members) {
            if (member.getUser().equals(user)) {
                member.setRole(role);
                break ;
            }
        }
    }
    
    public abstract String getType();
    public abstract Role getDefaultJoinRole();

}
