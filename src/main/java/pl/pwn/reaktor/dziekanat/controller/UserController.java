package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;

import java.io.IOException;

public class UserController {

    @FXML
    private Button btnUpdateData;

    @FXML
    private Label lblLogin;

    @FXML
    private Button btnSurvey;

    @FXML
    void SurveyAction(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/surveyView.fxml"));
        primaryStage.setTitle("Ankieta");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();
    }

    @FXML
    void UpdateDataEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/updateDataView.fxml"));
        primaryStage.setTitle("updateData");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();
    }

//aby w userViewerze wyświetlić powitanie zalogowanego, przypisuje do label'ki tekst "wita" i do tego dodaję login zalogowanego.
    public void initialize(){
        lblLogin.setText(lblLogin.getText() + CurrentUser.getCurrentUser().getLogin());
    }
}
