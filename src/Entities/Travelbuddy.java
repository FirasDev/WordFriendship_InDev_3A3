/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Yefet
 */
public class Travelbuddy {
    
    private int id ;
    private String name ;
    private String description ; 
    private String nationality  ;
    private int age ; 
    private List<String> languages ;
    private String destination ;    
    private List<String> preferncesCountry ;
    private String picture ;    
    private Date date_debut ;
    private Date date_fin ;
    private int id_user ;
    
    
      

    public Travelbuddy(String description, String destination, Date date_debut, Date date_fin, int id_user) {
        this.description = description;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
    }
      
      

    public Travelbuddy(int id, String name, String description, String nationality, int age, List<String> languages, String destination, String picture, Date date_debut, Date date_fin, int id_user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nationality = nationality;
        this.age = age;
        this.languages = languages;
        this.destination = destination;
        this.picture = picture;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.id_user = id_user;
        
    }
    
    
    public int getId_user() {
        return id_user;
    }

    public int getId_profil() {
        return id_profil;
    }
    private int id_profil;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public String getDestination() {
        return destination;
    }

    public List<String> getPreferncesCountry() {
        return preferncesCountry;
    }

    public String getPicture() {
        return picture;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }
    
    
}
