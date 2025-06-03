package manager;

import models.chat.Chat;
import models.chat.ChatMember;
import models.exception.NoPermissionException;
import models.exception.NotEditableMessageException;
import models.message.Message;
import models.user.User;

public class MessageManager {

    public void sendMessage(Chat chat, Message message) throws NoPermissionException
    {
        ChatMember sender = getChatMember(chat, message.getSender()) ;
        if (!sender.getRole().canSendMessage())
        {
            throw new NoPermissionException("send Message");
        }       
        chat.addMessage(message);
    }

    public void deleteMessage(Chat chat, User deletor, Message message) throws NoPermissionException {
        ChatMember deletorMember = getChatMember(chat, deletor) ;
        if(!deletorMember.getRole().canDeleteMessage(message, deletor))
        {
            throw new NoPermissionException("delete Message");
        }
        chat.deleteMessage(message);

    }

    public void editMessage(Chat chat, User editor, Message message, String newContent) throws NoPermissionException, NotEditableMessageException {

        ChatMember editorMember = getChatMember(chat, editor) ;
        if(editorMember.getRole().canEditMessage(message, editor))
        {
                message.edit(newContent);
        }
        else {
            throw new NoPermissionException("edit Message");
        }

    }     

    public void displayChatMessages(Chat chat) 
    {
        for (Message msg : chat.getMessages()) {
            msg.display();
        }
    }

    private ChatMember getChatMember(Chat chat, User user)
    {
        for (ChatMember member : chat.getMembers())
        {
            if (member.getUser().equals(user))
            {
                return member ;
            }
        }
        return null ;
    }
}
