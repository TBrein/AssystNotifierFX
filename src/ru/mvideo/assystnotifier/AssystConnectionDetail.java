package ru.mvideo.assystnotifier;

import javax.swing.*;

class AssystConnectionDetail {
    static final String DEF_SERVER_NAME = "10.95.1.56";
    private static final int DEF_PORT_NUMBER = 1433;
    private static final String DEF_DB_NAME = "mvideorus_db";

    private int portNumber;
    private String serverName;
    private String dbName;
    private String userLogin;
    private String userPassword;

    public AssystConnectionDetail(String login, String pass) {
        if (!login.equals("") && !pass.equals("")) {
            this.userLogin = login;
            this.userPassword = pass;
            this.portNumber = DEF_PORT_NUMBER;
            this.serverName = DEF_SERVER_NAME;
            this.dbName = DEF_DB_NAME;
        } else {
            JOptionPane.showMessageDialog(null, "Логин и пароль не должны быть пустыми!", "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
        }
    }

    public AssystConnectionDetail (String serverName, String dbName, int portNumber, String login, String pass){
        if (!login.equals("") && !pass.equals("") && !dbName.equals("") && !serverName.equals("") && portNumber > 0) {
            this.userLogin = login;
            this.userPassword = pass;
            this.portNumber = portNumber;
            this.serverName = serverName;
            this.dbName = dbName;
        } else {
            JOptionPane.showMessageDialog(null, "Использованы неверные данные!", "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
        }

    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

}
