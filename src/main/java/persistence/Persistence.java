package persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.List;

/**
 * Created by Mefju on 2016-05-06.
 */
@Configuration
public class Persistence {

    public Connection conn;
    public Statement stat;

    @Bean
    public Persistence autoConfigurePersistence(){return new Persistence();}


    public Persistence(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }
        startConnection();
        createTables();
    }

    public void startConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "154552");
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }

    private void createTables(){
        String createUsers = "CREATE TABLE IF NOT EXISTS Users (UserID SERIAL  PRIMARY KEY NOT NULL, Name varchar(255),  OAuth varchar(255))";
        String createBooks = "CREATE TABLE IF NOT EXISTS Books (BookID SERIAL  PRIMARY KEY NOT NULL, UserID INTEGER, Title varchar(255), Author varchar(255), Publisher varchar(255), Comment varchar(255), FOREIGN KEY(UserID) REFERENCES Users(UserID))";

        try {
            stat.execute(createUsers);
            stat.execute(createBooks);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }


    public void addBook(int userID, List<String> list){
            try {
                PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO Books(UserId, title, author, publisher, comment) values (?, ?, ?, ?, ?);");
                prepStmt.setInt(1, userID);
                prepStmt.setString(2, list.get(0));
                prepStmt.setString(3, list.get(1));
                prepStmt.setString(4, list.get(2));
                prepStmt.setString(5, list.get(3));
                prepStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        System.out.println("Added");
    }


}
