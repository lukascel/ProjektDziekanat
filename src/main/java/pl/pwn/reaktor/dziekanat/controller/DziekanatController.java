package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.DziekanatMain;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.service.LoginService;

import java.io.IOException;
import java.sql.SQLOutput;

public class DziekanatController {

    @FXML
    private Label lblLogin;

    @FXML
    private TextField tfLogin;

    @FXML
    private Label lblPassword;

    @FXML
    private PasswordField psPassword;

    @FXML
    private Button btShow;

    @FXML
    private Button btLogin;

    @FXML
    private Button btGuest;

    @FXML
    private Button btSignIn;

    @FXML
    private TextField tfPassword;

    @FXML
    void guestEvent(MouseEvent event)  throws IOException{
        Stage primaryStage = DziekanatMain.getPrimaryStage();

        Parent root = FXMLLoader.load(getClass().getResource("/viewers/guestView.fxml"));
        primaryStage.setTitle("GuestController");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();
    }

    @FXML
    void loginEvent(MouseEvent event) throws IOException {

        String login = tfLogin.getText();
        String pass = psPassword.isVisible()? psPassword.getText(): tfPassword.getText();

        LoginService loginService = new LoginService();
        User user = loginService.login(login, pass);

        if(user!=null){
            RoleEnum role = user.getRole();
            System.out.println("Zalogowano użytkownika: " + login + "o roli: " + role);

            if (RoleEnum.ROLE_STUDENT.equals(role)){
                Stage primaryStage = DziekanatMain.getPrimaryStage();

                Parent root = FXMLLoader.load(getClass().getResource("/viewers/userView.fxml"));
                primaryStage.setTitle("UserView");
                primaryStage.setScene(new Scene((root)));
                primaryStage.show();

            } else if (RoleEnum.ROLE_ADMIN.equals((role))) {
                Stage primaryStage = DziekanatMain.getPrimaryStage();

                Parent root = FXMLLoader.load(getClass().getResource("/viewers/adminView.fxml"));
                primaryStage.setTitle("AdminView");
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
            }
        }
    }

    @FXML
    void showPassEvent(MouseEvent event) {
        if("show".equalsIgnoreCase(btShow.getText())){
            tfPassword.setText(psPassword.getText());
            tfPassword.setVisible(true);
            psPassword.setVisible(false);
            btShow.setText("hide");
        } else if("hide".equalsIgnoreCase(btShow.getText())){
            psPassword.setText(tfPassword.getText());
            psPassword.setVisible(true);
            tfPassword.setVisible(false);
            btShow.setText("show");
        }
    }

    @FXML
    void signInEvent(MouseEvent event) throws IOException {
            Stage primaryStage = DziekanatMain.getPrimaryStage();

            Parent root = FXMLLoader.load(getClass().getResource("/viewers/signInView.fxml"));
            primaryStage.setTitle("signInView");
            primaryStage.setScene(new Scene((root)));
            primaryStage.show();
    }

    @FXML
    void loginKeyAction(KeyEvent event) throws IOException {
        if(KeyCode.ENTER.equals(event.getCode())){ //sprawdzam czy kod spod entera jest zgodny z event'em - z SceneBuilder'ra tam gdzie przypisywałem te eventy,
            loginEvent(null); //daje mu nulla, bo jak chce wchodzić enterem to nie za pomocą klikiem myszki. Po kliknieciu enter ma wywołać metodę z klikniecia myszką
        }
    }


}
