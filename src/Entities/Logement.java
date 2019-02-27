/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Entities;
package Entities;


import javafx.scene.text.Text;

/**
 *
 * @author DongFeng
 */
public class Logement {
    
    
    private int id ;
    private String nom ; 
    private String description ; 
    private int prix ; 
    private int nbr_place ; 
    private int id_cite ; 
    private int id_user ;
    private String nomc ;
    private String username ;
    private String URL ;

    public Logement() {
    }
 
    ///////////////////////////////////////////entiitieeeesssssss /////////////////////////////////
    
    public Logement( String nom, String description, int prix, int nbr_place,int nb_place ,int id_user) {        
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nbr_place = nb_place; ///////5deeeemttt 
        this.id_user = id_user;
    }
    
     public Logement( String nom, String description, int prix,int nb_place ,String URL) {        
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nbr_place = nb_place; ///////imaaaaaaageeeeeeeeeee
        this.id_user = id_user;
        this.URL= URL ;
    }
    
    
    public Logement(int id, String nom, String description, int prix, int nbr_place, int id_cite) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.nbr_place = nbr_place;    
        this.id_cite = id_cite;      
    }
    
    
   public Logement(String nom, String description, int prix, int nbr_place, int id_cite) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;               
        this.nbr_place = nbr_place;
        this.id_cite = id_cite;
        
    }
    public Logement(String nom, String description, int prix, int nbr_place) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;               
        this.nbr_place = nbr_place;      
    }
         public Logement(int id ,String nom, String description, int prix, int nbr_place) {
        this.id=id ;
             this.nom = nom;
        this.description = description;
        this.prix = prix;               
        this.nbr_place = nbr_place;      
    }
         
           public Logement(int id ,String nom, String description, int prix, int nbr_place,String nomc) {
        this.id=id ;
             this.nom = nom;
        this.description = description;
        this.prix = prix;               
        this.nbr_place = nbr_place; 
        this.nomc=nomc ;
    }
         public Logement(int id ,String nom, String description, int prix, int nbr_place,String nomc,String username) {    
        this.id=id ;
             this.nom = nom;
        this.description = description;
        this.prix = prix;               
        this.nbr_place = nbr_place; 
        this.nomc=nomc ;
        this.username=username;
    }    

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
           
           

    public int getId_user() {
        return id_user;
    }

    public String getNomc() {
        return nomc;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNomc(String nomc) {
        this.nomc = nomc;
    }
         
         
    
    
      ////////////////// Getteerssssss ////////////////////////////////
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getPrix() {
        return prix;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public int getId_cite() {
        return id_cite;
    }

    ////////////////////////settterrrsssss ////////////////
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setId_cite(int id_cite) {
        this.id_cite = id_cite;
    }

     //////////////////////////////////////////////////////////////// affichaageee //////////////////////////
    @Override
    public String toString() {
        return "Logement{" + "nom=" + nom + ", description=" + description + ", prix=" + prix + ", nbr_place=" + nbr_place +'}';
    }   
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
