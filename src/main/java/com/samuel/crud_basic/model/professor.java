package com.samuel.crud_basic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="professor") 
public class professor {

    /*
      atributos o columnas de la entidad  

    }*/
 
    @Id
    @Column(name="id_professor")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_professor;

    @Column(name="name", length=100, nullable = false)
    private String name;

    @Column(name="email", length=100, nullable = false)
    private String email;

    @Column(name="department", length=100, nullable = false)
    private String department;


    public professor(int id_professor, String name, String email, String department) {
      this.id_professor = id_professor;
      this.name = name;
      this.email = email;
      this.department = department;
    
    }

    public int getId_professor() {
      return id_professor;
    }

    public String getName(){
      return name;
    }

    public String getEmail(){
      return email;
    }

    public String getDepartment(){
      return department;
    }

    public void setId_professor(int id_professor) {
      this.id_professor = id_professor;
    }
    public void setName(String name) {
      this.name = name;
    }
    public void setEmail(String email) {
      this.email = email;
    }
    public void setDepartment(String department) {
      this.department = department;
    }

    
    
}
