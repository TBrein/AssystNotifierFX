package ru.mvideo.assystnotifier.PopupW;

import ru.mvideo.assystnotifier.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Math.round;

public class PopupWindow {

    private Stage popupWindow;

    public PopupWindow() {
        try {
            initWindow();
        } catch (IOException ignored) {

        }
    }

    private void initWindow() throws IOException {
        popupWindow = new Stage();
        popupWindow.setTitle("Dialog");
        popupWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("/ru/mvideo/assystnotifier/res/PopupWindow.fxml"))));
        popupWindow.sizeToScene();
        popupWindow.setResizable(false);
        popupWindow.getIcons().add(new Image(Main.class.getResource("/ru/mvideo/assystnotifier/res/icon32.png").toExternalForm()));
    }

    public Stage getInstance() throws IOException {
        if (popupWindow != null) return popupWindow;
        else {
            initWindow();
            return getInstance();
        }
    }

    public void showWindow() {
        popupWindow.show();
        popupWindow.setX(round(Screen.getPrimary().getVisualBounds().getWidth() / 2) - round(popupWindow.getWidth() / 2));
        popupWindow.setY(round(Screen.getPrimary().getVisualBounds().getHeight() / 2) - round(popupWindow.getHeight() / 2));
    }
}