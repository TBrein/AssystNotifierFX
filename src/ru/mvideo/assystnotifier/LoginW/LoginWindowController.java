package ru.mvideo.assystnotifier.LoginW;

import ru.mvideo.assystnotifier.MainW.MainWindow;
import ru.mvideo.assystnotifier.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ru.mvideo.assystnotifier.AssystConnectionDetail;
import ru.mvideo.assystnotifier.AssystDB;
import ru.mvideo.assystnotifier.User;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginWindowController {

    @FXML
    private Button loginBtn;

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    @FXML
    public void doLogin(ActionEvent event) throws IOException, SQLException {
        User user;
        AssystDB assystDB = new AssystDB(new AssystConnectionDetail(userLogin.getText(), userPassword.getText()));
        String userFIO = assystDB.getFullUserNameByLogin(userLogin.getText());
        if (!userFIO.equals("")) {
            MainWindow mw = new MainWindow();
            user = User.getInstance();
            user.setFullUserName(userFIO);
            mw.showWindow();
        } else
            JOptionPane.showMessageDialog(null, "Не удалось подключиться к БД ASSYST под логином - " + userLogin.getText(), "Ошибка подключения", JOptionPane.ERROR_MESSAGE);

        assystDB.closeConnection();
    }
}
