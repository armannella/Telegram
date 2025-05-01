package models.chat;

public class Group extends Chat{

    public Group(ChatMember owner , String title)
    {
        super();
        super.setTitle(title);
        addMember(owner);
    }

    @Override
    public String getType()
    {
        return "Group" ;
    }

    @Override
    public Role getDefaultJoinRole() {
        return new NormalUser(); 
    }
}
