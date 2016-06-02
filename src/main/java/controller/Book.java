package controller;


import java.io.Serializable;

/**
 * Created by Mefju on 2016-05-07.
 */
public class Book implements Serializable {

    public String title = null;
    public String author = null;
    public String publisher = null;
    public String comment = null;

    public Book(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
