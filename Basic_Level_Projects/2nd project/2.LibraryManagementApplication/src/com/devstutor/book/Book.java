package com.devstutor.book;

import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Getter & Setters

    public String getTitle(){return title;}
    public void setTitle(String title){ this.title = title;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre = genre;}

    public boolean isAvailable(){return available;}
    public void setAvailable(boolean available){this.available = available;}

    @Override
    public String toString(){
        return "[Title: "+title +", Author: "+author+", Genre: "+genre +", Available: "+available+"]";
    }
}
