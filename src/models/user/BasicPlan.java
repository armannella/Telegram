package models.user;

public class BasicPlan implements UserPlan {
    
    @Override
    public int getMaxPosts() {
        return 10 ;
    }

    @Override
    public boolean canCreateChannel(){
        return false;
    }

    @Override
    public boolean canCreateGroup(){
        return false ;
    }

    @Override
    public String getPlanName() {
        return "Basic" ;
    }


}
