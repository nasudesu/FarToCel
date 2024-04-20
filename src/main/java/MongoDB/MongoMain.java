package MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.bson.Document;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MongoMain extends Application {

    @Override
    public void start(Stage primaryStage){

        GridPane gridPane = getGridPane();



        // Creating scene
        Scene scene = new Scene(gridPane, 300, 250);
        // Setting stage title and scene
        primaryStage.setTitle("MongoDB CRUD Operations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static GridPane getGridPane() {
        Label idLabel = new Label("ID:");
        Label nameLabel = new Label("Name:");
        Label ageLabel = new Label("Age:");
        Label cityLabel = new Label("City:");

        // Creating text fields
        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField ageField = new TextField();
        TextField cityField = new TextField();

        // Creating buttons
        Button addButton = new Button("Add");
        Button readButton = new Button("Read");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction( event -> {
            System.out.println("Add button clicked");
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:txM8gQu4dV5wbRIo@cluster0.cx1wfws.mongodb.net/")){
                MongoDatabase database = mongoClient.getDatabase("company");
                MongoCollection<Document> collection = database.getCollection("employee");
                Document document = new Document()
                        .append("id", idField.getText())
                        .append("name", nameField.getText())
                        .append("age", ageField.getText())
                        .append("city", cityField.getText());
                collection.insertOne(document);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Document Added");
                alert.setHeaderText(null);
                alert.setContentText("Document added successfully.");
                alert.showAndWait();
            } catch (Exception e) {
                System.err.println("Error" + e);
            }
        });

        readButton.setOnAction( event -> {
            System.out.println("read clicked");
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:txM8gQu4dV5wbRIo@cluster0.cx1wfws.mongodb.net/")){
                MongoDatabase database = mongoClient.getDatabase("company");
                MongoCollection<Document> collection = database.getCollection("employee");
                String id = idField.getText();
                Document query = new Document("id", id);
                System.out.println("Query: " + query);
                Document document = collection.find(query).first();

                if (document != null) {
                    String _id = document.getObjectId("_id").toString();
                    String name = document.getString("name");
                    String age = document.getString("age");
                    String city = document.getString("city");

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Document Details");
                    alert.setHeaderText(null);
                    alert.setContentText("_id: " + _id + "\nName: " + name + "\nAge: " + age + "\nCity: " + city);
                    alert.showAndWait();
                    System.out.println("_id: " + _id + " Name: " + name + ", Age: " + age + ", City: " + city);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Document Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Document with ID " + id + " not found.");
                    alert.showAndWait();
                    System.out.println("Document with ID " + id + " not found.");
                }

            } catch (Exception e) {
                System.err.println("Error" + e);
            }
        });

        updateButton.setOnAction(event -> {
            System.out.println("Update button clicked");
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:txM8gQu4dV5wbRIo@cluster0.cx1wfws.mongodb.net/")) {
                MongoDatabase database = mongoClient.getDatabase("company");
                MongoCollection<Document> collection = database.getCollection("employee");

                // Find document by ID
                String id = idField.getText();
                Document query = new Document("id", id);
                Document document = collection.find(query).first();

                if (document != null) {
                    // Update document fields
                    Document updateFields = new Document();
                    if (!nameField.getText().isEmpty()) {
                        updateFields.append("name", nameField.getText());
                    }
                    if (!ageField.getText().isEmpty()) {
                        updateFields.append("age", ageField.getText());
                    }
                    if (!cityField.getText().isEmpty()) {
                        updateFields.append("city", cityField.getText());
                    }

                    // Perform the update operation
                    Document updateQuery = new Document("$set", updateFields);
                    collection.updateOne(query, updateQuery);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Document Updated");
                    alert.setHeaderText(null);
                    alert.setContentText("Document updated successfully.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Document Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Document with ID " + id + " not found.");
                    alert.showAndWait();
                    System.out.println("Document with ID " + id + " not found.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        });

        deleteButton.setOnAction(event -> {
            System.out.println("Delete button clicked");
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:<pasword>@cluster0.cx1wfws.mongodb.net/")) {
                MongoDatabase database = mongoClient.getDatabase("company");
                MongoCollection<Document> collection = database.getCollection("employee");

                // Find document by ID
                String id = idField.getText();
                Document query = new Document("id", id);
                Document document = collection.find(query).first();

                if (document != null) {
                    // Document found, delete it
                    collection.deleteOne(query);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Document Deleted");
                    alert.setHeaderText(null);
                    alert.setContentText("Document deleted successfully.");
                    alert.showAndWait();
                } else {
                    // Document not found, show warning
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Document Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Document with ID " + id + " not found.");
                    alert.showAndWait();
                    System.out.println("Document with ID " + id + " not found.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        });

        // Creating grid pane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Adding components to the grid pane
        gridPane.add(idLabel, 0, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(ageLabel, 0, 2);
        gridPane.add(cityLabel, 0, 3);

        gridPane.add(idField, 1, 0);
        gridPane.add(nameField, 1, 1);
        gridPane.add(ageField, 1, 2);
        gridPane.add(cityField, 1, 3);

        gridPane.add(addButton, 0, 4);
        gridPane.add(readButton, 1, 4);
        gridPane.add(updateButton, 0, 5);
        gridPane.add(deleteButton, 1, 5);
        return gridPane;
    }
}
