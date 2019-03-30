package pl.pwn.reaktor.dziekanat.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.dto.StudentDTO;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SignInService;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private TableView<StudentDTO> tvStudent;

    @FXML
    private TableColumn<StudentDTO, Long> colIdS;

    @FXML
    private TableColumn<StudentDTO, String> colLoginS;

    @FXML
    private TableColumn<StudentDTO, Boolean> colActiveS;

    @FXML
    private TableColumn<StudentDTO, String> colFirstNameS;

    @FXML
    private TableColumn<StudentDTO, String> colLastNameS;

    @FXML
    private TableColumn<StudentDTO, String> colStreetS;

    @FXML
    private TableColumn<StudentDTO, String> colCityS;

    @FXML
    private TableView<User> tvAdmin; //w< > podaję klasę z której biorę dane

    @FXML
    private TableColumn<User, Long> colIdA; //w< > podaję klasę i typ danej

    @FXML
    private TableColumn<User, String> colLoginA;

    @FXML
    private TableColumn<User, Enum> colRoleA;

    @FXML
    private TableColumn<User, Boolean> colActiveA;

    @FXML
    private MenuItem mClose;

    @FXML
    private MenuItem mLogOut;

    @FXML
    private MenuItem mAbout;

    @FXML
    void AboutAction(ActionEvent event) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle("About");
        info.setHeaderText("Instructions");
        info.setContentText("Instruction for using the form...");
        info.show();
    }

    @FXML
    void CloseAction(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void LogOutAction(ActionEvent event) throws IOException {
        CurrentUser.clean();
        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/dziekanatView.fxml"));
        primaryStage.setTitle("main View");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();

    }

    private SignInService signInService = new SignInService();

    public void initialize() {
        initAdminTable();
        initStudentTable();

    }

    private void initStudentTable() {
        //pobranie danych studenta z bazy danych:
        List<StudentDTO> students = signInService.getAllStudent();

        //konwersja na listę rozumianą przez tableView w Java FX
        ObservableList<StudentDTO> observableStudents = FXCollections.observableArrayList(students);
        tvStudent.setItems(null);
        tvStudent.setItems(observableStudents);

        //ustawienie poszczególnych kolumn, które z pola z User mają być widoczne i w jakiej kolumnie z widoku
        colIdS.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginS.setCellValueFactory(new PropertyValueFactory<>("login"));
        colActiveS.setCellValueFactory(new PropertyValueFactory<>("active"));
        colFirstNameS.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastNameS.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colStreetS.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCityS.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    private void initAdminTable() {
        //pobieranie danych adminów z bady danych - z userservice

        List<User> admins = signInService.getAllAdmin();


        //konwersja na listę rozumianą przez tableView w Java FX
        ObservableList<User> observableAdmins = FXCollections.observableArrayList(admins);
        tvAdmin.setItems(null);
        tvAdmin.setItems(observableAdmins);
        //ustawienie poszczególnych kolumn, które z pola z User mają być widoczne i w jakiej kolumnie z widoku
        colIdA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLoginA.setCellValueFactory(new PropertyValueFactory<>("login"));
        colRoleA.setCellValueFactory(new PropertyValueFactory<>("role"));
        colActiveA.setCellValueFactory(new PropertyValueFactory<>("active"));
    }

}
