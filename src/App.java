
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {

        // STEP 1: Create the layout
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12));
        pane.setHgap(5);
        pane.setVgap(5);

        // STEP 2: Create Labels and TextFields
        Label lblNumber = new Label("Number:");
        TextField tfNumber = new TextField();

        Label lblDescription = new Label("Description:");
        TextField tfDescription = new TextField();

        Label lblPrice = new Label("Price:");
        TextField tfPrice = new TextField();

        pane.add(lblNumber, 0, 0);
        pane.add(tfNumber, 1, 0);

        pane.add(lblDescription, 0, 1);
        pane.add(tfDescription, 1, 1);

        pane.add(lblPrice, 0, 2);
        pane.add(tfPrice, 1, 2);

        // STEP 3: Create Buttons
        Button btSave = new Button("Save");
        Button btQuit = new Button("Quit");

        pane.add(btSave, 0, 3);
        pane.add(btQuit, 1, 3);

        // STEP 4a: Save Button Functionality
        btSave.setOnAction(e -> {

            String product = tfNumber.getText() + ", "
                    + tfDescription.getText() + ", "
                    + tfPrice.getText();

            writeToFile(product);

            // Clear all fields
            tfNumber.clear();
            tfDescription.clear();
            tfPrice.clear();
        });

        // STEP 4b: Quit Button Functionality
        btQuit.setOnAction(e -> Platform.exit());

        // STEP 5: Create and Show Scene
        Scene scene = new Scene(pane, 400, 250);

        stage.setTitle("Inventory Recording");
        stage.setScene(scene);
        stage.show();
    }

    // STEP 6: Write product information to file
    private void writeToFile(String text) {

        try (FileWriter writer = new FileWriter("Products.txt", true)) {

            writer.write(text + System.lineSeparator());

        } catch (IOException e) {

            System.out.println("Error writing to file: " + e.getMessage());

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
