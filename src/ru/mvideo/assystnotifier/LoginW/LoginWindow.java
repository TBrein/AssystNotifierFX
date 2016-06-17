package ru.mvideo.assystnotifier.LoginW;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.mvideo.assystnotifier.Main;

import java.io.IOException;

public class LoginWindow {

    private Stage loginWindow;

    public LoginWindow() {
        try {
            initWindow();
        } catch (IOException ignored) {

        }
    }

    private void initWindow() throws IOException {
        loginWindow = new Stage();
        loginWindow.setTitle("MVIDEO ASSYST NOTIFIER");
        loginWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("/ru/mvideo/assystnotifier/res/loginWindow.fxml")), 300, 290));
        loginWindow.setResizable(false);
        loginWindow.getIcons().add(new Image(Main.class.getResource("/ru/mvideo/assystnotifier/res/icon32.png").toExternalForm()));
    }

    public Stage getInstance() {
        if (loginWindow != null) return loginWindow;
        else {
            try {
                initWindow();
            } catch (IOException e) {
                return null;
            }
            return getInstance();
        }
    }

    public void showWindow() {
        loginWindow.show();
        loginWindow.setX(Screen.getPrimary().getVisualBounds().getWidth() - loginWindow.getWidth());
        loginWindow.setY(Screen.getPrimary().getVisualBounds().getHeight() - loginWindow.getHeight());

    }
}
