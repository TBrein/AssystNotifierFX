package ru.mvideo.assystnotifier;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssystDB {

    private Connection con = null;

    private AssystDB() {

    }

    public Connection getInstance(AssystConnectionDetail assystConnectionDetail) {
        if (con != null)
            return con;
        else {
            if (assystConnectionDetail != null) {
                SQLServerDataSource ds = new SQLServerDataSource();
                ds.setServerName(assystConnectionDetail.getServerName());
                ds.setPortNumber(assystConnectionDetail.getPortNumber());
                ds.setDatabaseName(assystConnectionDetail.getDbName());
                ds.setUser(assystConnectionDetail.getUserLogin());
                ds.setPassword(assystConnectionDetail.getUserPassword());
                try {
                    return ds.getConnection();
                } catch (SQLServerException e) {
                    JOptionPane.showMessageDialog(null, "Не удалось подключиться к БД ASSYST под логином - " + assystConnectionDetail.getUserLogin(), "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(null, "AssystConnectionDetail is null!", "Ошибка подключения", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public ResultSet executeSQLQuery(Connection con, String sqlQuery) {
        try {
            return con.prepareCall(sqlQuery).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
