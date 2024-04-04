package GUI2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GUI2 extends Application {

    ResourceBundle resourceBundle;
    Label nameLabel;
    Label lastNameLabel;
    Label emailLabel;
    Button submitButton;

    String selected;

    TextField nameField;
    TextField lastNameField;
    TextField emailField;

    public void updateStrings(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("message", locale);
        nameLabel.setText(resourceBundle.getString("firstName"));
        lastNameLabel.setText(resourceBundle.getString("lastName"));
        emailLabel.setText(resourceBundle.getString("email"));
        submitButton.setText(resourceBundle.getString("submit"));
    }

    @Override
    public void start(Stage stage) throws Exception {

        Locale enUK = new Locale("en", "UK");
        Locale fiFI = new Locale("ja", "JP");
        Locale faIR = new Locale("fa", "IR");

        resourceBundle = ResourceBundle.getBundle("message", enUK);

        String firstName = resourceBundle.getString("firstName");
        String lastName = resourceBundle.getString("lastName");
        String email = resourceBundle.getString("email");
        String submit = resourceBundle.getString("submit");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("English", "Farsi", "Japanese");
        choiceBox.setOnAction(e -> {
            selected = choiceBox.getValue();
            switch (selected) {
                case "English" -> updateStrings(enUK);
                case "Farsi" -> updateStrings(faIR);
                case "Japanese" -> updateStrings(fiFI);
            }
        });

        nameLabel = new Label(firstName);
        nameField = new TextField();

        lastNameLabel = new Label(lastName);
        lastNameField = new TextField();

        emailLabel = new Label(email);
        emailField = new TextField();

        submitButton = new Button(submit);
        submitButton.setOnAction(event -> {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fxdemo", "root", "ohjelmisto1");
                String sql = switch (selected) {
                    case "English" -> "INSERT INTO employee_en (first_name, last_name, email) VALUES (?, ?, ?)";
                    case "Farsi" -> "INSERT INTO employee_fa (first_name, last_name, email) VALUES (?, ?, ?)";
                    case "Japanese" -> "INSERT INTO employee_ja (first_name, last_name, email) VALUES (?, ?, ?)";
                    default -> null;
                };
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, nameField.getText());
                statement.setString(2, lastNameField.getText());
                statement.setString(3, emailField.getText());
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Employee data inserted successfully!");
                } else {
                    System.out.println("Failed to insert employee data.");
                }
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });

        gridPane.add(choiceBox, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(lastNameLabel, 0, 2);
        gridPane.add(lastNameField, 1, 2);
        gridPane.add(emailLabel, 0, 3);
        gridPane.add(emailField, 1, 3);
        gridPane.add(submitButton, 1, 4);

        Scene scene = new Scene(gridPane, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Form Example");
        stage.show();
    }
}
