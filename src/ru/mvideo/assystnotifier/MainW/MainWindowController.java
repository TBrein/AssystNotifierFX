package ru.mvideo.assystnotifier.MainW;

import ru.mvideo.assystnotifier.PopupW.PopupWindow;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import ru.mvideo.assystnotifier.User;

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
        User user;
        user = User.getInstance();
        welcomeUserText.setText(String.format(welcomeUserText.getText(), user.getFullUserName()));

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

                PopupWindow pw = new PopupWindow();
                pw.showWindow();
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