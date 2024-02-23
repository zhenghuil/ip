package reacher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import reacher.command.Command;

import java.time.format.DateTimeParseException;

public class Reacher {
    private final Storage storage = new Storage("./storage.txt");
    private final TaskList tasks = new TaskList(storage.loadList());
    private final Ui ui = new Ui();

    public Reacher() {
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            return "reply";
        } catch (ReacherException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Prints welcome message and takes in user input, executes it until user exits.
     */
    public void run() {
        ui.printWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.print("Enter a command:");
                String input = ui.readString();
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (ReacherException e) {
                ui.print(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.print("Pls provide correct format: yyyy-mm-dd");
            }
        }
    }
}
