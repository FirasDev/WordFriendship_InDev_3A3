/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Timestamp;

/**
 *
 * @author Firas
 */
public class User {
	protected int id;
	protected String username;
	protected String email;
	protected Boolean enabled;
	protected String salt;
	protected String password;
	protected String last_login;
	protected String roles;
	protected String firstname;
	protected String lastname;
        protected String nationalite;
        protected String langues;
        
        
public User(){}

public User( String username, String email, Boolean enabled,  String roles, String firstname, String lastname, String nationalite,String langues)
{
   
    this.username = username;
    this.email = email;
    this.enabled = enabled;
    this.roles = roles;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;

}     
public User( String username, String email,  String password, String firstname, String lastname, String nationalite,String langues)
{
   
    this.username = username;
    this.email = email;
    
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;

}     


        
public User( int id, String username, String email,String password, String firstname, String lastname, String nationalite,String langues)
{
    this.id=id; 
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;

} 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean email) {
        this.enabled = enabled;
    }
    
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
    
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
    
    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }
    
    public String getLangues() {
        return langues;
    }

    public void setLangues(String langues) {
        this.langues = langues;
    }
    
 /* @Override
    public String toString() {
        return "User{" +"username=" + username + ", email=" + email +", enabled=" + enabled+", roles=" + roles+ ", firstname=" + firstname+", lastname=" + lastname+", nationalite=" + nationalite+", langues=" + langues+'}';
    }  
    */
}