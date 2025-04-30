package models.message;
import models.user.*;
import util.DateTimeUtil;

public class TextMessage extends Message {

    private String content ;
    private boolean edited = false ;


    public TextMessage (User sender ,String content)
    {
        super(sender);
        this.content = content ;
    }

    
    public String getContent()
    {
        return this.content;
    }


    @Override
    public void display()
    {   
        String info = "-"+ this.getID() + " | " + this.getSender().getNickname() + " : " +this.content + " (" + DateTimeUtil.format(timestamp) + ")" ;
        if(edited)
        {
            info += " (Edited)";
        }
        System.out.println(info);
    }

    public void edit(String content)
    {
        this.content = content ;
        edited = true ;
    }

    public boolean isEdited()
    {
        return this.edited ;
    }


}
