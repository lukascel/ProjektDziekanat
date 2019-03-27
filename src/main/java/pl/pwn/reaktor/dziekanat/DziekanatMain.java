package pl.pwn.reaktor.dziekanat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pwn.reaktor.dziekanat.utils.HibernateUtils;

public class DziekanatMain extends Application { //extendsApplication - mamy nadpisaną metodę start więc wykonuje się zawsze, na początku, jakby main.

    private static Stage primaryStage;
    //W Java FX - metoda uzywana do uruchomienia projektu bez kompilatora tylko z wyeksportowanego jar'a
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/viewers/dziekanatView.fxml"));
        primaryStage.setTitle("Logowanie");
        primaryStage.setScene(new Scene((root)));
        primaryStage.show();

    }
    // W JavaFX - metoda stosowana TYLKO w InteliJ/Eclipse do uruchomienia projektu, uruchamia docelowo metodę start
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private static void setPrimaryStage(Stage primaryStage) {
        DziekanatMain.primaryStage = primaryStage;
    }


    //dwie metody nieabstrakcyjne, wcale nie trzeba ich stosować:
    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Metoda init uruchamiana tylko raz przy starcie projektu");
        HibernateUtils.initSessionFactory();
    }

    @Override
    public void stop() throws Exception {
        super.init();
        System.out.println("Metoda stop uruchamiana tylko raz przy zamykaniu projektu");
        HibernateUtils.destroySessionFactory();
    }
}

