package util;

import app.MainApp;
import models.exception.UserAlreadyExistsException;

public class inputs {

    public static void add_some_members()
    {
        try {
            MainApp.userManager.register("armannella", "1010", "Arman", "3517803215");
            MainApp.userManager.register("mahyaaf", "1020", "Mahya", "09174448711");
            MainApp.userManager.register("alsopa", "1030", "Alireza", "09351974322");
            MainApp.userManager.register("amirrezanfari", "1040", "Amir", "09122343753");
        } 
        catch (UserAlreadyExistsException e){
            System.out.println(e.getMessage());
        }
        
        
    }

}
