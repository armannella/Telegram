package models.chat;

import models.message.Message;
import models.user.User;

public interface Role {
    boolean canSendMessage();
    boolean canEditMessage(Message message, User editor);
    boolean canDeleteMessage(Message message, User deleter);
    boolean canAddMember();
    boolean canDeleteMember();
    boolean canManageAdmins();
    boolean canChangeTopic();
    String getRoleName();
}
