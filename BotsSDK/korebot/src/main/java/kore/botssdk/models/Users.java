package kore.botssdk.models;

public class Users {

    private String UserId;
    private String userName;
    private String UserFullName;
    private String password;

    public Users(){}

    public Users(String userId, String userName, String userFullName, String password) {
        this.UserId = userId;
        this.userName = userName;
        this.UserFullName = userFullName;
        this.password = password;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFullName() {
        return UserFullName;
    }

    public void setUserFullName(String userFullName) {
        UserFullName = userFullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
