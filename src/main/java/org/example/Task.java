package org.example;
//Realizado por Richard Montoya (marca de agua) jajaja
public class Task {
    private String title;
    private String description;
    private String state;

    public Task(String title, String description, String state){
        this.title = title;
        this.description = description;
        this.state = state;
    }
//Realizado por Richard Montoya (marca de agua) jajaja
    public String getTitle(){
        return this.title;
    }
    public String getDescription(){
        return this.description;
    }
    public String getState(){
        return this.state;
    }
    public void setState(String state){
        this.state = state;
    }
}
//Realizado por Richard Montoya (marca de agua) jajaja