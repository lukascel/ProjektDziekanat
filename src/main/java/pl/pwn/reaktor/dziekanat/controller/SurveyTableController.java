package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SurveyService;

import java.io.IOException;
import java.util.List;

public class SurveyTableController {

    @FXML
    private TableView<Survey> tbvSurvey;

    @FXML
    private TableColumn<Survey, Long> tcId;

    @FXML
    private TableColumn<Survey, String> tcFirstName;

    @FXML
    private TableColumn<Survey, String> tcLastName;

    @FXML
    private TableColumn<Survey, String> tcMail;

    @FXML
    private TableColumn<Survey, String> tcPhone;

    @FXML
    private TableColumn<Survey, Boolean> tcJava;

    @FXML
    private TableColumn<Survey, Boolean> tcPython;

    @FXML
    private TableColumn<Survey, Boolean> tcOther;

    @FXML
    private TableColumn<Survey, String> tcOtherDesc;

    @FXML
    private TableColumn<Survey, String> tcLanguage;

    @FXML
    private TableColumn<Survey, String> tcCourse;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    SurveyService surveyService = new SurveyService();

    @FXML
    void BackEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/viewers/userView.fxml"));
        primaryStage.setTitle("GuestController");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();
    }

    @FXML
    void DeleteEvent(MouseEvent event) {

        if(tbvSurvey.getSelectionModel() !=null && tbvSurvey.getSelectionModel().getSelectedItem() !=null) {

            Survey selectedItem = tbvSurvey.getSelectionModel().getSelectedItem();
            surveyService.delete(selectedItem);
            initSurveyToTable();

        }
    }

    public void initialize(){
        initSurveyToTable();

        tcId.setCellValueFactory(new PropertyValueFactory<>("id")); //w stringu są pola z obiektu przekazanego powyzej - w ObservableList
        tcFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tcLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tcMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tcPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tcJava.setCellValueFactory(new PropertyValueFactory<>("java"));
        tcPython.setCellValueFactory(new PropertyValueFactory<>("python"));
        tcOther.setCellValueFactory(new PropertyValueFactory<>("other"));
        tcOtherDesc.setCellValueFactory(new PropertyValueFactory<>("otherDesc"));
        tcLanguage.setCellValueFactory(new PropertyValueFactory<>("language"));
        tcCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
    }

    private void initSurveyToTable() {
        List<Survey> surveys = surveyService.getSurveyByStudent(CurrentUser.getCurrentUser().getStudent().getId());

        tbvSurvey.setItems(null);
        ObservableList<Survey> observableSyrvey = FXCollections.observableArrayList(surveys);
        tbvSurvey.setItems(observableSyrvey);
    }

}
