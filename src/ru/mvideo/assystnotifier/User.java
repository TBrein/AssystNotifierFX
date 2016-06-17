package ru.mvideo.assystnotifier;

public class User {
    private static User user;
    private String fullUserName;
    private String userLogin;
    private IncidentList incidents;

    private User() {
        fullUserName = "";
        userLogin = "";
        incidents = new IncidentList();
    }

    public static User getInstance() {
        if (user != null) return user;
        else {
            user = new User();
            return user;
        }
    }

    public String getFullUserName() {
        return fullUserName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public IncidentList getUserIncidents() {
        return incidents;
    }

    public void setFullUserName(String fullUserName) {
        this.fullUserName = fullUserName;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserIncidents(IncidentList incidents) {
        this.incidents = incidents;
    }
}
