package GUI1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class GUI extends Application {

    ResourceBundle resourceBundle;

    Button button;
    Button button2;
    Button button3;
    Label label;

    public void updateStrings() {
        String content1 = resourceBundle.getString("button1");
        String content2 = resourceBundle.getString("button2");
        String content3 = resourceBundle.getString("button3");
        String name = resourceBundle.getString("name");

        button.setText(content1);
        button2.setText(content2);
        button3.setText(content3);
        label.setText(name);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();

        Locale enUK = new Locale("en", "UK");
        Locale fiFI = new Locale("ja", "JP");
        Locale faIR = new Locale("fa", "IR");

        resourceBundle = ResourceBundle.getBundle("message", enUK);

        String content1 = resourceBundle.getString("button1");
        String content2 = resourceBundle.getString("button2");
        String content3 = resourceBundle.getString("button3");
        String name = resourceBundle.getString("name");

        button = new Button(content1);
        button2 = new Button(content2);
        button3 = new Button(content3);

        label = new Label(name);
        root.getChildren().addAll(button, button2, button3, label);

        button.setOnAction(e -> {
            resourceBundle = ResourceBundle.getBundle("message", enUK);
            updateStrings();
        });

        button2.setOnAction(e -> {
            resourceBundle = ResourceBundle.getBundle("message", fiFI);
            updateStrings();
        });

        button3.setOnAction(e -> {
            resourceBundle = ResourceBundle.getBundle("message", faIR);
            updateStrings();
        });

        Scene scene = new Scene(root, 200, 200);
        stage.setTitle("Localization");
        stage.setScene(scene);
        stage.show();
    }
}

