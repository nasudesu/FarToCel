package MongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.bson.Document;

public class test {
    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create("mongodb+srv://nasudesu:txM8gQu4dV5wbRIo@cluster0.cx1wfws.mongodb.net/")){
            MongoDatabase database = mongoClient.getDatabase("company");
            MongoCollection<Document> collection = database.getCollection("employee");
            String id = "1";
            Document query = new Document("id", id);
            System.out.println("Query: " + query);
            Document document = collection.find(query).first();

            if (document != null) {
                // Document found, you can now access its fields
                String _id = document.getObjectId("_id").toString();
                String name = document.getString("name");
                String age = document.getString("age");
                String city = document.getString("city");

                // Do something with the retrieved data
                System.out.println("_id: " + _id + " Name: " + name + ", Age: " + age + ", City: " + city);
            } else {
                System.out.println("Document with ID " + id + " not found.");
            }

        } catch (Exception e) {
            System.err.println("Error" + e);
        }
    }
}
