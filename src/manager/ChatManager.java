package manager;

import java.util.ArrayList;
import models.chat.*;
import models.message.ActionType;
import models.message.InfoMessage;
import models.user.User;

public class ChatManager {

    private ArrayList<Chat> chats ;

    public ChatManager()
    {
        chats = new ArrayList<>();
    }

    public PV createPrivateChat(User user1, User user2)
    {
        PV pv = new PV(new ChatMember(user1, new NormalUser()), new ChatMember(user2, new NormalUser())) ;
        chats.add(pv);
        pv.addMessage(new InfoMessage(user1, ActionType.CREATED));
        return pv ;
    }
    public Group createGroupChat(User owner,String title)
    {
        Group group = new Group(new ChatMember(owner, new Owner()),title);
        chats.add(group);
        group.addMessage(new InfoMessage(owner, ActionType.CREATED));
        return group;
    }

    public Channel createChannel(User owner,String title)
    {
        Channel channel = new Channel(new ChatMember(owner, new Owner()),title);
        chats.add(channel);
        channel.addMessage(new InfoMessage(owner, ActionType.CREATED));
        return channel;
    }


    public ArrayList<Chat> getChatsOfUser(User user)
    {
        ArrayList<Chat> userchats = new ArrayList<>();
        for (Chat chat : chats)
        {
            if(chat.isUserinChat(user))
            {
                userchats.add(chat) ;
            }
        }
        return userchats;
    }

    
    public Chat getChatById(int id)
    {
        Finder<Chat> finder = new Finder<>();
        return finder.findByID(chats, id);
    }


    public boolean chatExists(int id)
    {
        for (Chat chat : chats)
        {
            if(chat.getID() == id)
            {
                return true ;
            }
        }
        return false ;
    }

}
