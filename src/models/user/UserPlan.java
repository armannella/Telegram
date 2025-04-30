package models.user;

public interface UserPlan {

    int getMaxPosts();
    boolean canCreateChannel();
    boolean canCreateGroup();
    String getPlanName();
    
}
