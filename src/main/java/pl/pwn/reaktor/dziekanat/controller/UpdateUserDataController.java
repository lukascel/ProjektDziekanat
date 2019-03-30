package pl.pwn.reaktor.dziekanat.controller;

import com.google.protobuf.StringValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.Address;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.model.utils.CurrentUser;
import pl.pwn.reaktor.dziekanat.service.SignInService;
import pl.pwn.reaktor.dziekanat.service.StudentService;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class UpdateUserDataController {

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfFirstName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfStreet;

    @FXML
    private TextField tfCity;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnBack;

    @FXML
    void BackEvent(MouseEvent event) throws IOException {
        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/userView.fxml"));
        primaryStage.setTitle("UserController");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();
    }

    @FXML
    void SaveEvent(MouseEvent event) {
        User user = CurrentUser.getCurrentUser();

        StudentService studentService = new StudentService();
//        Student student = Objects.isNull(user.getStudent()) ? new Student(): user.getStudent();
//        user.setStudent(student);

        Student student = user.getStudent();
        if (Objects.isNull(user.getStudent())){
            student= new Student();

            //zapis studenta do db

            studentService.save(student);

            user.setStudent(student);

            SignInService signInService = new SignInService();
            signInService.update(user);
        }

        student.setFirstName(tfFirstName.getText());
        student.setLastName(tfLastName.getText());

        Address address = new Address(tfStreet.getText(), tfCity.getText());
        student.setAddress(address);

        try {
            studentService.update(student);
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setContentText("Succes Student Error \n");
            info.setTitle("Update Student");
            info.show();

        } catch (Exception e){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Update Student Error \n" + e);
            error.setTitle("Error update Student");
            error.show();
        }
    }


    public void initialize(){
        User currentUser = CurrentUser.getCurrentUser();
        Optional.ofNullable(currentUser.getStudent())
                .ifPresent(this::mapToForm);
    }

//pobieranie danych z forularza z viewera:

    private void mapToForm(Student s) {
        tfId.setText(String.valueOf(s.getId()));

        tfFirstName.setText(Objects.nonNull(s.getFirstName()) ? s.getFirstName() : "");

        tfLastName.setText(Optional.ofNullable(s.getLastName()).orElse(""));

        if (Objects.nonNull(s.getAddress())) {
            tfStreet.setText(Objects.isNull(s.getAddress().getStreet()) ? "" : s.getAddress().getStreet());

            tfCity.setText(Optional.ofNullable(s.getAddress().getCity()).orElse(""));
        }
    }
}
