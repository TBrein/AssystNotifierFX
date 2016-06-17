package ru.mvideo.assystnotifier;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import ru.mvideo.assystnotifier.LoginW.LoginWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        LoginWindow lw = new LoginWindow();
        lw.showWindow();
//        addAppToTray();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addAppToTray() {
        try {
            java.awt.Toolkit.getDefaultToolkit();
            java.awt.TrayIcon trayIcon = null;

            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            java.awt.Image image = ImageIO.read(Main.class.getResource("res/icon16.png"));

            trayIcon = new java.awt.TrayIcon(image);

//            trayIcon.addActionListener(event -> Platform.runLater(this::show));

            java.awt.MenuItem openItem = new java.awt.MenuItem("Показать приложение");
//            openItem.addActionListener(event -> Platform.runLater(Main.this::show));

            java.awt.Font defaultFont = java.awt.Font.decode(null);
            java.awt.Font boldFont = defaultFont.deriveFont(java.awt.Font.BOLD);
            openItem.setFont(boldFont);

            java.awt.MenuItem exitItem = new java.awt.MenuItem("Выход");
            TrayIcon finalTrayIcon = trayIcon;
            exitItem.addActionListener(event -> {
                tray.remove(finalTrayIcon);
                Platform.exit();
            });

            // создаем popup меню
            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);

            // добавляем в трей trayIcon
            tray.add(trayIcon);
        } catch (java.awt.AWTException | IOException e) {
            System.out.println("Не могу инциализировать трей");
            e.printStackTrace();
        }
    }
}