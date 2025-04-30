package models.user;

public class GoldPlan implements UserPlan{

    @Override
    public int getMaxPosts() {
        return 30 ;
    }

    @Override
    public boolean canCreateChannel(){
        return true;
    }

    @Override
    public boolean canCreateGroup(){
        return true ;
    }

    @Override
    public String getPlanName() {
        return "Gold" ;
    }

}


