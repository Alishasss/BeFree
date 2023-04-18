package com.example.befree;

public class State_Categor {
    private String name;
    private int ideaResource;
    public  State_Categor (String name,int ideaResource){
        this.name=name;
        this.ideaResource=ideaResource;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public  int getIdeaResource(){
        return this.ideaResource;
    }
    public void setIdeaResource(int ideaResource){
        this.ideaResource=ideaResource;
    }
}
