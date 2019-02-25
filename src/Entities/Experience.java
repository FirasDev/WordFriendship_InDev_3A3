/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Firas
 */
public class Experience {
    
    private int id_experience;
    private String Titre_exp;
    private String type_exp;
    private String desc_exp;
    private Date date_exp;
    private float eval_exp;
    private int id_pays;
    private int id_user;
    private String image;
    private int id_album_experience;
    private String name_country;
    private String username;
    
    

    
    public Experience(String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp) {
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
    }

    public Experience(int id_experience, String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp) {
        this.id_experience = id_experience;
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
    }

    public Experience(String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays, String image) {
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
        this.image = image;
    }

    public Experience(int id_experience, String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays) {
        this.id_experience = id_experience;
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
    }
    
    
    
    public Experience(String Titre_exp,String type_exp,String desc_exp,Date date_exp,float eval_exp,int id_pays){
    
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays; 
    }

    public Experience(int id_experience, String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays, String name_country) {
        this.id_experience = id_experience;
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
        this.name_country = name_country;
    }

    public Experience(int id_experience, String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays, String name_country, int id_user, String username) {
        this.id_experience = id_experience;
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
        this.id_user = id_user;
        this.name_country = name_country;
        this.username = username;
    }



    public Experience(String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays, int id_user, String image, int id_album_experience) {
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
        this.id_user = id_user;
        this.image = image;
        this.id_album_experience = id_album_experience;
    }

    public Experience(String Titre_exp, String type_exp, String desc_exp, Date date_exp, float eval_exp, int id_pays, String image, int id_user) {
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        this.id_pays = id_pays;
        this.id_user = id_user;
        this.image = image;
    }

    

    public int getId_experience() {
        return id_experience;
    }

    public void setId_experience(int id_experience) {
        this.id_experience = id_experience;
    }

    public String getTitre_exp() {
        return Titre_exp;
    }

    public void setTitre_exp(String Titre_exp) {
        this.Titre_exp = Titre_exp;
    }

    public String getType_exp() {
        return type_exp;
    }

    public void setType_exp(String type_exp) {
        this.type_exp = type_exp;
    }

    public String getDesc_exp() {
        return desc_exp;
    }

    public void setDesc_exp(String desc_exp) {
        this.desc_exp = desc_exp;
    }

    public Date getDate_exp() {
        return date_exp;
    }

    public void setDate_exp(Date date_exp) {
        this.date_exp = date_exp;
    }

    public float getEval_exp() {
        return eval_exp;
    }

    public void setEval_exp(float eval_exp) {
        this.eval_exp = eval_exp;
    }

    public int getId_pays() {
        return id_pays;
    }

    public void setId_pays(int id_pays) {
        this.id_pays = id_pays;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_album_experience() {
        return id_album_experience;
    }

    public void setId_album_experience(int id_album_experience) {
        this.id_album_experience = id_album_experience;
    }

    public String getName_country() {
        return name_country;
    }

    public void setName_country(String name_country) {
        this.name_country = name_country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Experience{" + "id_experience=" + id_experience + ", Titre_exp=" + Titre_exp + ", type_exp=" + type_exp + ", desc_exp=" + desc_exp + ", date_exp=" + date_exp + ", eval_exp=" + eval_exp + ", id_pays=" + id_pays + ", id_user=" + id_user + ", image=" + image + ", id_album_experience=" + id_album_experience + ", name_country=" + name_country + ", username=" + username + '}';
    }

    
    

    

    
    
    }
 
