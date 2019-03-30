package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pl.pwn.reaktor.dziekanat.model.RoleEnum;
import pl.pwn.reaktor.dziekanat.model.User;
import pl.pwn.reaktor.dziekanat.service.SignInService;

public class SignInController {
    @FXML
    private TextField tfSignInLogin;

    @FXML
    private PasswordField pfSignInPass;

    @FXML
    private Button btCreateAccount;

    @FXML
    void createAccountEvent(MouseEvent event) {

        String userName = tfSignInLogin.getText();
        String password = pfSignInPass.getText();
        User user = new User(userName, password, RoleEnum.ROLE_STUDENT, true);

        SignInService signInService = new SignInService();
        long saveUser = signInService.save(user);
        System.out.println("SignIn user id: " + saveUser);

    }









}
