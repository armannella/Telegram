package models.user;

public class SilverPlan implements UserPlan{

    @Override
    public int getMaxPosts() {
        return 20 ;
    }

    @Override
    public boolean canCreateChannel(){
        return false;
    }

    @Override
    public boolean canCreateGroup(){
        return true ;
    }

    @Override
    public String getPlanName() {
        return "Silver" ;
    }

}
