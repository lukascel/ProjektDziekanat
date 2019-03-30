package pl.pwn.reaktor.dziekanat.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class SurveyController {

    @FXML
    private MenuItem mSaveToFile;

    @FXML
    private MenuItem mSendEmail;

    @FXML
    private MenuItem mSaveToDatabase;

    @FXML
    private MenuItem mClear;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mAbout;

    @FXML
    private CheckBox cbJava;

    @FXML
    private CheckBox cbPython;

    @FXML
    private CheckBox cbOther;

    @FXML
    private Button btnPreview;

    @FXML
    private RadioButton rbBasic;

    @FXML
    private ToggleGroup G1;

    @FXML
    private RadioButton rbIntermediate;

    @FXML
    private RadioButton rbAdvanced;

    @FXML
    private ComboBox<?> cmbCourses;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfPhone;

    @FXML
    void AboutAction(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Survey Information");
        info.setHeaderText("This is some info about survey: ");
        info.setContentText("Instructions, how to fulfill survey correctly");
        info.show();
    }

    @FXML
    void ClearAction(ActionEvent event) {

    }

    @FXML
    void CloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void OtherAction(MouseEvent event) {

    }

    @FXML
    void PreviewAction(MouseEvent event) {

    }

    @FXML
    void SaveToDBAction(ActionEvent event) {

    }

    @FXML
    void SaveToFileAction(ActionEvent event) {

    }

    @FXML
    void SendMailAction(ActionEvent event) {

    }

}
