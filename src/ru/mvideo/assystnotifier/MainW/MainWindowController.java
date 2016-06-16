package ru.mvideo.assystnotifier.MainW;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.mvideo.assystnotifier.Main;

import javax.swing.JOptionPane;

import java.io.IOException;

import static java.lang.Math.round;

public class MainWindowController {

    private FadeTransition ft;

    @FXML
    private Button startNotifyingBtn;

    @FXML
    private Label welcomeUserText;

    @FXML
    private ProgressBar autostartBar;

    @FXML
    private TableView<?> IncidentsTable;

    @FXML
    void startNotifying(ActionEvent event) {
//        ft.stop();
//        startNotifyingBtn.setText("Остановить отслеживание");
//        autostartBar.setVisible(false);
    }

    @FXML
    void stopAutostart(ActionEvent event) {
        ft.stop();
        startNotifyingBtn.setText("Остановить отслеживание");
        autostartBar.setVisible(false);
    }

    @FXML
    void initialize() {
        welcomeUserText.setText(String.format(welcomeUserText.getText(), "Бедарев Вячеслав Сергеевич"));

        ft = new FadeTransition(Duration.millis(600), autostartBar);

        new Thread(() -> {
            while (autostartBar.getProgress() < 1) {
                autostartBar.setProgress(autostartBar.getProgress() + 0.02);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Error in Thread!");
                }
            }
            Platform.runLater(() -> {
                startNotifyingBtn.setText("Остановить отслеживание");
                ft.stop();
                autostartBar.setVisible(false);

                Stage primaryStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/ru/mvideo/assystnotifier/res/PopupWindow.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.setTitle("Dialog");
                primaryStage.setScene(new Scene(root));
                primaryStage.sizeToScene();
                primaryStage.setResizable(false);
                primaryStage.getIcons().add(new Image(Main.class.getResource("/ru/mvideo/assystnotifier/res/icon32.png").toExternalForm()));
                primaryStage.show();
                primaryStage.setX(round(Screen.getPrimary().getVisualBounds().getWidth() / 2) - round(primaryStage.getWidth() / 2));
                primaryStage.setY(round(Screen.getPrimary().getVisualBounds().getHeight() / 2) - round(primaryStage.getHeight() / 2));


            });
//            JOptionPane.showMessageDialog(null, "Запущен процесс отслеживания назначений инцидентов...", "Автостарт", JOptionPane.INFORMATION_MESSAGE);
        }).start();

        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.setCycleCount(Animation.INDEFINITE);
//        ft.setCycleCount(30);
        ft.setAutoReverse(true);
//        ft.setOnFinished(e -> { });
        ft.play();
    }
}