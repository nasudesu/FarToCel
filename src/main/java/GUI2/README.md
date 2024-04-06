### Overview:
This JavaFX application is a simple form interface that allows users to input their name, last name, and email address. The entered information is then saved into a MySQL database. The application supports multilingual functionality, allowing users to switch between English, Farsi, and Japanese languages.

### Features:
- **Multilingual Support:** Users can select their preferred language (English, Farsi, or Japanese) from a dropdown menu. The interface elements dynamically update according to the selected language.

- **Database Integration:** Entered user information is saved into the database based on the selected language after user press 'Submit' button. Three separate tables (`employee_en`, `employee_fa`, and `employee_ja`) are utilized for storing data.

### Database Configuration:
    
- Create a database named `fxdemo` with tables `employee_en`, `employee_fa`, and `employee_ja`, each with columns `first_name`, `last_name`, `email` and `id` that will be auto incremented.

