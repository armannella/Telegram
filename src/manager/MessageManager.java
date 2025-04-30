package manager;

import models.chat.Chat;
import models.chat.ChatMember;
import models.message.Message;
import models.message.TextMessage;
import models.user.User;

public class MessageManager {

    public boolean sendMessage(Chat chat, Message message)
    {
        ChatMember sender = getChatMember(chat, message.getSender()) ;
        if (sender.getRole().canSendMessage())
        {
            chat.addMessage(message);
            return true ;
        }

        return false;
    }


    public boolean deleteMessage(Chat chat, User deletor, Message message) {
        ChatMember deletorMember = getChatMember(chat, deletor) ;
        if(deletorMember.getRole().canDeleteMessage(message, deletor))
        {
            chat.deleteMessage(message);
            return true ;
        }
        return false ;

    }

    public boolean editMessage(Chat chat, User editor, Message message, String newContent) {

        ChatMember editorMember = getChatMember(chat, editor) ;
        if(editorMember.getRole().canEditMessage(message, editor))
        {
            if(message instanceof TextMessage)
            {
                ((TextMessage) message).edit(newContent);
                return true ;
            }
            
        }
        return false ;

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
