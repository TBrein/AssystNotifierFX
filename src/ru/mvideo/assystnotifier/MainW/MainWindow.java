package ru.mvideo.assystnotifier.MainW;

import ru.mvideo.assystnotifier.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {

    private Stage mainWindow;

    public MainWindow() {
        try {
            initWindow();
        } catch (IOException ignored) {

        }
    }

    private void initWindow() throws IOException {
        mainWindow = new Stage();
        mainWindow.setTitle("Main Window");
        mainWindow.setScene(new Scene(FXMLLoader.load(getClass().getResource("/ru/mvideo/assystnotifier/res/mainWindow.fxml")), 470, 420));
        mainWindow.setResizable(false);
        mainWindow.getIcons().add(new Image(Main.class.getResource("/ru/mvideo/assystnotifier/res/icon32.png").toExternalForm()));
        mainWindow.show();
    }

    public Stage getInstance() {
        if (mainWindow != null) return mainWindow;
        else {
            try {
                initWindow();
            } catch (IOException e) {
                return null;
            }
            return mainWindow;
        }
    }

    public void showWindow() {
        mainWindow.show();
        mainWindow.setX(Screen.getPrimary().getVisualBounds().getWidth() - mainWindow.getWidth());
        mainWindow.setY(Screen.getPrimary().getVisualBounds().getHeight() - mainWindow.getHeight());

    }

}