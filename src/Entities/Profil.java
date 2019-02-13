/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Jamila
 */
public class Profil { //psudo langues email nom prenom

    private int id_p;
    private int id_up;
    private Date date_naissance;
    private String photo_p;
    private String descriptions;

    public Profil() {
    }



    public Profil(int id_up, Date date_naissance, String photo_p, String descriptions) {

        this.id_up = id_up;
        this.date_naissance = date_naissance;
        this.photo_p = photo_p;
        this.descriptions = descriptions;

    }
      public Profil(Date date_naissance, String photo_p, String descriptions) {

        
        this.date_naissance = date_naissance;
        this.photo_p = photo_p;
        this.descriptions = descriptions;

    }

public Profil(int id_up, Date date_naissance,  String descriptions) {

        this.id_up = id_up;
        this.date_naissance = date_naissance;
    
        this.descriptions = descriptions;

    }
   

   

    public int getId() {
        return id_p;
    }

    public void setId(int id_p) {
        this.id_p = id_p;
    }

    public int getId_UP() {
        return id_up;
    }

    public void setId_UP(int id_up) {
        this.id_up = id_up;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
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
User u;
    @Override
    public String toString() {
        return "profil{" + " date_naissance=" + date_naissance + ", photo_p=" + photo_p + ", descriptions=" + descriptions +", username=" + u.username + ", email=" + u.email +", roles=" + u.roles +", firstname=" + u.firstname +", lastname=" + u.lastname +", nationalite=" + u.nationalite+", langues=" + u.langues +'}';
        
    }

}
