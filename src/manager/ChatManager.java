package manager;

import java.util.ArrayList;
import models.chat.*;
import models.exception.IdNotFoundException;
import models.exception.NoPermissionException;
import models.exception.UserAlreadyInChatException;
import models.exception.UserNotInChat;
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

    public Group createGroupChat(User owner,String title) throws NoPermissionException
    {
        if(!owner.getUserplan().canCreateGroup()) {
            throw new NoPermissionException("Create Group .");
        }
        Group group = new Group(new ChatMember(owner, new Owner()),title);
        chats.add(group);
        group.addMessage(new InfoMessage(owner, ActionType.CREATED));
        return group;
    }

    public Channel createChannel(User owner,String title) throws NoPermissionException
    {
        if(!owner.getUserplan().canCreateChannel()) {
            throw new NoPermissionException("Create Channel .");
        }
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

    public Chat getChatById(int id) throws IdNotFoundException
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


    public void addMember(Chat chat, User actor, User target) throws NoPermissionException, UserAlreadyInChatException {

        Role actorRole = chat.getRoleofUser(actor);
        if (!actorRole.canAddMember()) {
            throw new NoPermissionException("add member");
        }

        if (chat.isUserinChat(target)) {
            throw new UserAlreadyInChatException();
        }
        if (chat instanceof PV) {
            throw new NoPermissionException("add members to PV chat");
        }

        ChatMember newMember = new ChatMember(target, chat.getDefaultJoinRole());
        chat.addMember(newMember);
    }

    public void removeMember(Chat chat, User actor, User target) throws NoPermissionException, UserNotInChat {

        Role actorRole = chat.getRoleofUser(actor);
        if (!actorRole.canDeleteMember()) {
            throw new NoPermissionException("remove members");
        }

        if (!chat.isUserinChat(target)) {
            throw new UserNotInChat();
        }

        if (chat instanceof PV) {
            throw new NoPermissionException("remove members from PV chat");
        }
        chat.removeMember(target);
    }

    public void promoteToAdmin(Chat chat, User actor, User target) throws NoPermissionException, UserNotInChat {

        Role actorRole = chat.getRoleofUser(actor);
        if (!actorRole.canManageAdmins()) {
            throw new NoPermissionException("promote admins");
        }

        if (!chat.isUserinChat(target)) {
            throw new UserNotInChat();
        }
        chat.changeRoleOfUser(target, new Admin());
    }

    public void demoteFromAdmin(Chat chat, User actor, User target) throws NoPermissionException, UserNotInChat {

        Role actorRole = chat.getRoleofUser(actor);
        if (!actorRole.canManageAdmins()) {
            throw new NoPermissionException("demote admins");
        }

        if (!chat.isUserinChat(target)) {
            throw new UserNotInChat();
        }

        chat.changeRoleOfUser(target, chat.getDefaultJoinRole());
    }

    public void changeTitle(Chat chat, User user, String newtitle) throws NoPermissionException {

        Role actorRole = chat.getRoleofUser(user);
        if (!actorRole.canChangeTopic()) {
            throw new NoPermissionException("change chat title");
        }

        chat.setTitle(newtitle);
    }

}
