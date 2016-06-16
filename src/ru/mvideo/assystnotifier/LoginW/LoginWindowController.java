package ru.mvideo.assystnotifier.LoginW;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.mvideo.assystnotifier.Main;

import java.io.IOException;

import static java.lang.Math.round;

public class LoginWindowController {

    @FXML
    private Button loginBtn;

    @FXML
    public void doLogin(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ru/mvideo/assystnotifier/res/mainWindow.fxml"));
        System.out.println("Прошли mainWin");
        primaryStage.setTitle("Main Window");
        primaryStage.setScene(new Scene(root, 470, 420));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResource("/ru/mvideo/assystnotifier/res/icon32.png").toExternalForm()));
        primaryStage.show();
        primaryStage.setX(round(Screen.getPrimary().getVisualBounds().getWidth() / 2) - round(primaryStage.getWidth() / 2));
        primaryStage.setY(round(Screen.getPrimary().getVisualBounds().getHeight() / 2) - round(primaryStage.getHeight() / 2));
    }
}
