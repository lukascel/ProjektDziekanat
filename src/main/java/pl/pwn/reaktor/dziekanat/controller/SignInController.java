package pl.pwn.reaktor.dziekanat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

        SignInService signInService = new SignInService();
        User user = signInService.signIn(userName, password);
    }









}
