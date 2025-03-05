package com.samuel.crud_basic.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="library")

public class library {

    @Id
    @Column(name="id_library")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_library;

    @Column(name="name", length=100, nullable=false)
    private String name;

    @Column(name="location", length=100, nullable=false)
    private String location;
    
    //Constructor

    public library(int id_library, String name, String location) {
        this.id_library = id_library;
        this.name = name;
        this.location = location;
    }

    public int getId_library(){
        return id_library;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }

    public void  setId_library(int id_library){
        this.id_library = id_library;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLocation(String location){
        this.location = location;
    }

    
}
