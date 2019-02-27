/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Firas
 */
public class User {

    public static void add(User p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
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
        protected String date_naissance;
        protected String photo_p;
        protected String descriptions;
        protected String sexe;
        protected int etat;
        protected int tel;
        protected int code;
         
        
public User(){}

public User( String username, String email, Boolean enabled,  String roles, String firstname, String lastname, String nationalite,String langues,String date_naissance, String photo_p, String descriptions)
{
   
    this.username = username;
    this.email = email;
    this.enabled = enabled;
    this.roles = roles;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
     this.date_naissance = date_naissance;
     this.photo_p = photo_p;
     this.descriptions = descriptions;

}   


public User( String username, String email,String password, String firstname, String lastname, String nationalite,String langues,String date_naissance, String photo_p, String descriptions)
{
   
    this.username = username;
    this.email = email;
    this.password = password;
   
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    this.date_naissance=date_naissance;
    this.photo_p = photo_p;
    this.descriptions = descriptions;
     

} 

//ajout
public User( String username, String email, String password,String firstname, String lastname, String nationalite,String langues,String date_naissance, String photo_p, String descriptions,String sexe,int tel)
{
   
    this.username = username;
    this.email = email;
    this.password = password;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    this.date_naissance=date_naissance;
    this.photo_p = photo_p;
    this.descriptions = descriptions;
    this.sexe = sexe;
    this.tel = tel;
     

} 
public User( int id, String username, String email, Boolean enabled,  String roles, String firstname, String lastname, String nationalite,String langues,String date_naissance, String photo_p, String descriptions)
{
    this.id= id;
    this.username = username;
    this.email = email;
    this.enabled = enabled;
    this.roles = roles;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    this.date_naissance = date_naissance;
    this.photo_p = photo_p;
    this.descriptions = descriptions;

}  


        

//modif
public User( int id,String username, String firstname, String lastname, String nationalite,String langues, String date_naissance, String photo_p, String descriptions,String sexe,int tel)
{
    this.id = id;
    this.username = username;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    this.date_naissance=date_naissance;
    this.photo_p=photo_p;
    this.descriptions=descriptions;
    this.sexe=sexe;
    this.tel=tel;
    

}

/*public User (String username,String firstname,String lastname,String langue,String date,String descr,String sexe)
{
    this.username=username;
    this.firstname=firstname;
    this.lastname=this.lastname;
    this.langues=langue;
    this.date_naissance=date;
    this.descriptions=descr;
    this.sexe=sexe;
}*/



        
/*public User(String username, String email, String firstname, String lastname, String nationalite,String langues,String photo_p,String descriptions)
{
   
    this.username = username;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    this.photo_p = photo_p;
    this.descriptions = descriptions;

} */

    public User(int id, String username, String email, String firstname, String lastname, String nationalite, String langues, String descriptions, String sexe) 
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nationalite = nationalite;
        this.langues = langues;
        this.descriptions = descriptions;
        this.sexe = sexe;
    }
    
public User(String username, String email, String firstname, String lastname, String nationalite,String langues,String descriptions,String sexe)
{
   
    this.username = username;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
   
    this.descriptions = descriptions;
    this.sexe=sexe;

} 

public User( int id, String username, String email)
{
    this.id=id; 
    this.username = username;
    this.email = email;
    
}

public User(int id, String username, String firstname, String lastname)
{
    this.id = id;
    this.username=username; 
    this.firstname = firstname;
    this.lastname = lastname;
    
}
public User( int id,String password)
{
    this.id=id; 
    this.password=password;
    
} 
public User(String firstname, String lastname)
{
    
    this.firstname = firstname;
    this.lastname = lastname;
    
} 
public User(int id, String email, String roles, Boolean enabled)
{
    this.id = id;
    this.email = email;
   // this.password = password;
    this.roles = roles;
    this.enabled = enabled;
    
} 



        
public User(String email,String firstname, String lastname, String nationalite,String langues,String descriptions,String sexe)
{
   
    this.email = email;
   
    this.firstname = firstname;
    this.lastname = lastname;
    this.nationalite = nationalite;
    this.langues = langues;
    
    this.descriptions = descriptions;
     this.sexe=sexe;

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
    
    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPhoto_p() {
        return photo_p;
    }

    public void setPhoto_p(String photo_p) {
        this.photo_p = photo_p;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
     public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    
     public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
     public int getCode() {
        return code;
    }
    
    public void setCode(int code)
    {
        this.code = code;
    }
    
    public int getTel() {
        return tel;
    }
    
    public void setTel(int tel)
    {
        this.tel = tel;
    }
    
 /* @Override
    public String toString() {
        return "User{" +"username=" + username + ", email=" + email +", enabled=" + enabled+", roles=" + roles+ ", firstname=" + firstname+", lastname=" + lastname+", nationalite=" + nationalite+", langues=" + langues+'}';
    }  
    */
/*
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", firstname=" + firstname + ", lastname=" + lastname + ", nationalite=" + nationalite + ", langues=" + langues + ", descriptions=" + descriptions + ", sexe=" + sexe + '}';
 */

    @Override
    public String toString() {
        return "User{" + "id=" + id + " firstname=" + firstname + ", lastname=" + lastname + '}';
    }

}