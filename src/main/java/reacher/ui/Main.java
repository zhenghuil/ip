package reacher.ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import reacher.Reacher;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Reacher reacher = new Reacher();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setReacher(reacher);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
