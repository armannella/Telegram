package models.user;

public class User {
    private String username ;
    private String password ;
    private String nickname ;
    private String phonenumber ;
    private Profile profile ;
    private UserPlan userplan ;


    public User (String username , String password , String nickname , String phonenumber) {

        this.username = username ;
        this.password = password ;
        this.phonenumber = phonenumber ;
        this.nickname = nickname ;
        this.profile = new Profile() ;
        this.userplan = new BasicPlan();
    }


    public boolean checkPassword(String password) {
        return this.getPassword().equals(password) ;
    }


/* Setter and Getters Methods */

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
  
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
   
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
   
    public UserPlan getUserplan() {
        return userplan;
    }

    public void setUserplan(UserPlan userplan) {
        this.userplan = userplan;
    }
}



