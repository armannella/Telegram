package models.chat;

public class PV extends Chat{

    public PV(ChatMember one , ChatMember two)
    {
        super();
        super.addMember(one);
        super.addMember(two);
        super.setTitle(one.getUser().getNickname() + " - " + two.getUser().getNickname());
    }

    @Override
    public String getType()
    {
        return "PV" ;
    }

    @Override
    public Role getDefaultJoinRole() {
        return new NormalUser(); 
    }
    
}
