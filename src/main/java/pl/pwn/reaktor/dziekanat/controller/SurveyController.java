package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

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
    private ComboBox<String> cmbCourses;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfMail;

    @FXML
    private TextField tfPhone;

    @FXML
    private TextField tfOther;

    @FXML
    private TextArea taPreview;

    @FXML
    private Button btBack;


    @FXML
    void BackAction(MouseEvent event) throws IOException {

        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/userView.fxml"));
        primaryStage.setTitle("UserControler");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();

    }

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
        tfLastName.clear();
        tfMail.clear();
        tfName.clear();
        tfPhone.clear();
        cbJava.setSelected(false);
        cbOther.setSelected(false);
        tfOther.clear();
        tfOther.setDisable(true);
        cbPython.setSelected(false);
        rbIntermediate.setSelected(true);
        cmbCourses.setValue(null);
        taPreview.clear();
    }

    @FXML
    void CloseAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void OtherAction(MouseEvent event) {
        if(cbOther.isSelected()){
            tfOther.setDisable(false);
            tfOther.setEditable(true);
        } else {
            tfOther.setDisable(true);
            tfOther.clear();}
    }

    @FXML
    void PreviewAction(MouseEvent event) {

        if(isNotCompleted()){
            showAlertNotCompleted();
            return; // może go nie być i wtedy wypełnia w oknie to co już wypełniłem.
        }
        String value = getSurveyText();
        taPreview.setText(value);
    }

    private String getSurveyText() {
        String name = tfName.getText();
        String lastName = tfLastName.getText();
        String mail = tfMail.getText();
        String phone = tfPhone.getText();
        String java = cbJava.isSelected() ? cbJava.getText(): "";
        String python = cbPython.isSelected() ? cbPython.getText(): "";
        String other = cbOther.isSelected() ? tfOther.getText() : "";
        String language = "";
        if(rbBasic.isSelected()){
            language= rbBasic.getText();
        }
        if(rbIntermediate.isSelected()){
            language= rbIntermediate.getText();
        }
        if(rbAdvanced.isSelected()){
            language= rbAdvanced.getText();
        }
        String course = cmbCourses.getValue();

        return "Imię: " + name + "\nNazwisko: " + lastName + "\nE-mail: " + mail + "\nPhone: " + phone + "\n " + "\n" +
                "Wybrane kursy: " + java + " " + python + " " + other + "\n" + "\n" + "Language: " + language + "\n" + "\n" + "Course: " + course;
    }

    private void showAlertNotCompleted() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("Survey Alert");
        info.setHeaderText("You have to fill out every field in survey! ");
        info.setContentText("Plese, fill out needed fields in survey correctly");
        info.show();
    }

    private boolean isNotCompleted() {
        return "".equals(tfName.getText()) || "".equals(tfLastName.getText()) || "".equals(tfMail.getText()) || "".equals(tfPhone.getText()) || !(cbJava.isSelected() || cbPython.isSelected() || cbOther.isSelected())
        || Objects.isNull(cmbCourses.getValue());
    }

    @FXML
    void SaveToDBAction(ActionEvent event) {
        String name = tfName.getText();
        String lastName = tfLastName.getText();
        String mail = tfMail.getText();
        String phone = tfPhone.getText();
        Boolean java = cbJava.isSelected();
        Boolean python = cbPython.isSelected();
        String otherDesc = cbOther.isSelected() ? tfOther.getText() : "";
        Boolean other = cbOther.isSelected();
        String language = "";
        if(rbBasic.isSelected()){
            language= rbBasic.getText();
        }
        if(rbIntermediate.isSelected()){
            language= rbIntermediate.getText();
        }
        if(rbAdvanced.isSelected()){
            language= rbAdvanced.getText();
        }
        String course = cmbCourses.getValue();

        //tworzę obiekt, ale zeby go zrobic to jeszcze konstruktor bez Id w modelu!
        Survey survey = new Survey(name, lastName, mail, phone, java, python, other, otherDesc, language, course, CurrentUser.getCurrentUser().getStudent().getId());

        //biorę metodę zapisującą do db z surveyservice
        SurveyService surveyService = new SurveyService();
        surveyService.save(survey);

    }

    @FXML
    void SaveToFileAction(ActionEvent event) {
        if(isNotCompleted()){
            showAlertNotCompleted();
        } else {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save resource file");
            File file = fileChooser.showSaveDialog(null);
            String filePath = file.getPath();

            //klasa try with resources - tworzy obiekt, wykonuje try i go zamyka. try i ()
            try(PrintWriter save = new PrintWriter(filePath)) {
                save.println(getSurveyText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void SendMailAction(ActionEvent event) {

    }

        public void initialize(){
            ObservableList<String> courses = FXCollections.observableArrayList("Back-End Developer", "Python Developer", "SQL Manager", "Auto Tester");
            cmbCourses.setItems(courses);
        }

}
