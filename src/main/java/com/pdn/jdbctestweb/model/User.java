package com.pdn.jdbctestweb.model;

import org.springframework.stereotype.Component;
  
@Component
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
     
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString(){
        return "id: "+getId()+" username: "+getUsername()+" password: "+getPassword()+" firstname: "+getFirstname()+" lastname: "+getLastname()+" email: "+getEmail();
    }
}