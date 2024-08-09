

## How to Use the Application

### Step 1: Clone the Repository

Clone the repository to make a local copy of the application's code repository onto your machine using the appropriate version control system (e.g., Git).

### Step 2: Configure Database Settings
-If you are using MariaDB, the project has been set up so that MariaDB is the primary database. You can also use MySQL or other database ,
but you will need to install the necessary dependencies and configure the database in the application.properties or application.yml file.


### Step 3: Create a database named librarydb.

 

### Step 4: Install Dependencies

1. **Navigate to the library_management_system Folder:**

   - Open the directory containing the library_management_system code.

2. **Open a Terminal:**

   - Launch a terminal window or command prompt to execute command-line instructions.

3. **Install Dependencies:**
   - Run the following command to install the required dependencies using mvn:
     ```bash
     mvn clean install
     ```



### Step 5: Verify Database Connection

 **Ensure Database Connection:**

   - Make sure your database server is running and accessible.


### Step 6: Run The Project : 
  **Execute link Command:**
   - Run the following command to compile and run the project. :
     ```bash
     mvn spring-boot:run
     ```

 

 ### Step 7 : Create a new token by registering a user :

create a new record in the users table so that you can use the project and access the various endpoints. Since I have implemented a basic authentication, it only requires a unique username and password. This will result in the creation of a token that can be used to send requests to the server.

**Additional Notes:**

- **Books endpoints:**

  - in post and put you need to send title, author, publicationYear,isbn .
  -thw last one is unique 
   ```json
   {
     "title": "The Great Gatsby",
     "author": "F. Scott Fitzgerald",
     "publicationYear": 1925,
     "isbn": "9780743273565"
   }
   ```


    
- **patrons endpoints:**

  - in post and put you need to send name, phone, email .
  -the last one is unique

   ```json
   {
     "name": "test",
     "phone": "0106456986",
     "email": "test@test.com"
   }
    ```

 

**Seeking Feedback:**

- I eagerly await your feedback and suggestions to improve the results and address any potential issues.
