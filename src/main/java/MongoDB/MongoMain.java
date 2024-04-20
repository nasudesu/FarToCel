package MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
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
            try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:txM8gQu4dV5wbRIo@cluster0.ltnadjt.mongodb.net/")){
                MongoDatabase database = mongoClient.getDatabase("test");
                MongoCollection<Document> collection = database.getCollection("products");
                Document document = new Document()
                        .append("id", 1)
                        .append("name", "sample")
                        .append("pric", 19.99)
                        .append("description", "sdfsdfsdfsdfsdfsdf");
                collection.insertOne(document);
            } catch (Exception e) {
                System.err.println("Error" + e);
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
