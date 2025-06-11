package incidentmanager;

import incidentmanager.view.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainView tela = new MainView();
        tela.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
