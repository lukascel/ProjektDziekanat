package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    void OtherAction(MouseEvent event) {

    }

    @FXML
    void PreviewAction(MouseEvent event) {

    }

}
