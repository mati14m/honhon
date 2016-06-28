package honhon.persistence;

import honhon.controller.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mefju on 2016-05-06.
 */
@Configuration
public class Persistence {

    public Connection conn;
    public Statement stat;

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
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "154552");
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "hittdgwyvbhcuk", "4R4bkOlL1v6DxCR_J4KAoBhKrf");
            //stat = conn.createStatement();
            URI dbUri = new URI("postgres://fvuovesjubhyju:ZahB4tA5nFiO2RQxHBTOrx7I-t@ec2-54-83-31-65.compute-1.amazonaws.com:5432/d6efq7os3m5aff");

            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            conn = DriverManager.getConnection(dbUrl, username, password);
            stat = conn.createStatement();


        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        } catch (URISyntaxException e) {
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
        //String drop = "DROP TABLE BOOKS";
        //String drop2 = "DROP TABLE Users";
        try {

            //stat.execute(drop);
            //stat.execute(drop2);
            stat.execute(createUsers);
            stat.execute(createBooks);
        } catch (SQLException e) {
            System.err.println("Blad przy tworzeniu tabeli");
            e.printStackTrace();
        }
    }


    public void addBook(String OAuth, List<String> list){
            try {
                PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO Books(UserId, title, author, publisher, comment) values (?, ?, ?, ?, ?);");
                prepStmt.setInt(1, userID(OAuth));
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

    public void deleteBook(String OAuth, String title){
        try {
            stat.executeUpdate("DELETE FROM Books WHERE Title =" + "'" + title + "' AND UserID = (SELECT UserID FROM Users WHERE OAuth =" + "'" + OAuth + "')");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<Book>();
        try {
            ResultSet rs = stat.executeQuery("SELECT Title, Author, Publisher, Comment, Name FROM Books JOIN Users ON Users.UserID = Books.UserID");
            while(rs.next()){
                Book tmp = new Book();
                tmp.setTitle(rs.getString(1));
                tmp.setAuthor(rs.getString(2));
                tmp.setPublisher(rs.getString(3));
                tmp.setComment(rs.getString(4));
                tmp.setOwner(rs.getString(5));
                books.add(tmp);
            }
            rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return books;
    }

    public List<Book> getMyBooks(String OAuth){
        List<Book> books = new ArrayList<Book>();
        try {
            ResultSet rs = stat.executeQuery("SELECT Title, Author, Publisher, Comment FROM Books JOIN Users ON Users.UserID = Books.UserID WHERE OAuth =" + "'" + OAuth + "'");
            while(rs.next()){
                Book tmp = new Book();
                tmp.setTitle(rs.getString(1));
                tmp.setAuthor(rs.getString(2));
                tmp.setPublisher(rs.getString(3));
                tmp.setComment(rs.getString(4));
                tmp.setOwner("");
                books.add(tmp);
            }
            rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return books;
    }

    public List<Book> getNotMyBooks(String OAuth){
        List<Book> books = new ArrayList<Book>();
        try {
            ResultSet rs = stat.executeQuery("SELECT Title, Author, Publisher, Comment, Name FROM Books JOIN Users ON Users.UserID = Books.UserID WHERE OAuth !=" + "'" + OAuth + "'");
            while(rs.next()){
                Book tmp = new Book();
                tmp.setTitle(rs.getString(1));
                tmp.setAuthor(rs.getString(2));
                tmp.setPublisher(rs.getString(3));
                tmp.setComment(rs.getString(4));
                tmp.setOwner(rs.getString(5));
                books.add(tmp);
            }
            rs.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return books;
    }

    public void addUser(String name, String OAuth){
        try {
            PreparedStatement prepStmt = conn.prepareStatement("INSERT INTO Users(UserId, Name, OAuth) values (DEFAULT, ?, ?);");
            prepStmt.setString(1, name);
            prepStmt.setString(2, OAuth);
            prepStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean findUser(String OAuth){
        try {
            ResultSet rs = stat.executeQuery("SELECT * FROM Users WHERE OAuth =" + "'" + OAuth + "'");
            while(rs.next()){
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int userID(String OAuth){
        try {
            ResultSet rs = stat.executeQuery("SELECT UserID FROM Users WHERE OAuth =" + "'" + OAuth + "'");
            while(rs.next()){
                int result = rs.getInt(1);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String userName(int userID){
        try {
            ResultSet rs = stat.executeQuery("SELECT Name FROM Users WHERE OAuth =" + "'" + userID + "'");
            while(rs.next()){
                String result = rs.getString(1);
                rs.close();
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Brak";
    }


}
