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
    
    public Experience(int id_experience, String Titre_exp,String type_exp,String desc_exp,Date date_exp,float eval_exp){
    
        this.id_experience = id_experience;
        this.Titre_exp = Titre_exp;
        this.type_exp = type_exp;
        this.desc_exp = desc_exp;
        this.date_exp = date_exp;
        this.eval_exp = eval_exp;
        
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
    
    
}
