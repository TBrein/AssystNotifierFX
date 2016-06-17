package ru.mvideo.assystnotifier;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssystDB {

    private Connection con = null;

    public AssystDB(AssystConnectionDetail assystConnectionDetail) {
        initConnection(assystConnectionDetail);
    }

    private void initConnection(AssystConnectionDetail assystConnectionDetail) {
        if (con == null) {
            if (assystConnectionDetail != null) {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName(assystConnectionDetail.getServerName());
                ds.setPortNumber(assystConnectionDetail.getPortNumber());
                ds.setDatabaseName(assystConnectionDetail.getDbName());
                ds.setUser(assystConnectionDetail.getUserLogin());
                ds.setPassword(assystConnectionDetail.getUserPassword());
                try {
                    con = ds.getConnection();
                } catch (SQLServerException e) {
                    JOptionPane.showMessageDialog(null, "Не удалось подключиться к БД ASSYST под логином - " + assystConnectionDetail.getUserLogin(), "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(null, "AssystConnectionDetail is null!", "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ResultSet executeSQLQuery(String sqlQuery) {
        try {
            return con.prepareCall(sqlQuery).executeQuery();
        } catch (SQLException e) {
//            Ошибка выполнения SQL скрипта
        }
        return null;
    }

    public String getFullUserNameByLogin(String login) throws SQLException {
        ResultSet rs;
        rs = executeSQLQuery(String.format("SELECT u.assyst_usr_n FROM assyst_usr u WHERE u.assyst_usr_sc = '%s';", login));
        if (rs != null && rs.next()) {
            return rs.getString("assyst_usr_n");
        } else {
            JOptionPane.showMessageDialog(null, "Не удалось получить ФИО пользователя под логином \"" + login + "\" ", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
