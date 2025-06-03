package models.message;

import models.exception.NotEditableMessageException;
import models.user.*;
import util.DateTimeUtil;

public class MultimediaMessage extends Message {
    
    private String imagePath ;

    public MultimediaMessage (User sender ,String imagePath)
    {
        super(sender);
        this.imagePath = imagePath ;
    }

    public String getImagePath()
    {
        return this.imagePath;
    }

    @Override
    public void display()
    {
        String info = "-"+ id + " | File | " + sender.getNickname() + " : " + imagePath + " (" + DateTimeUtil.format(timestamp) + ")" ;
        System.out.println(info);
    }

    @Override
    public boolean editable() {
        return false;
    }

    @Override
    public void edit(String newContent) throws NotEditableMessageException{
        throw new NotEditableMessageException("MultiMedia");
    }
}
