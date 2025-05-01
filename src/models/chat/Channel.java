package models.chat;

public class Channel extends Chat{

    public Channel(ChatMember owner , String title)
    {
        super();
        super.setTitle(title);
        addMember(owner);

    }

    @Override
    public String getType()
    {
        return "Channel" ;
    }

    @Override
    public Role getDefaultJoinRole() {
        return new Viewer(); 
    }
}
