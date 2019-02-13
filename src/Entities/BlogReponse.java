/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;


/**
 *
 * @author Marwen
 * 
 */
public class BlogReponse 
{
    private int id_reponse_question;
    private int id_question;
    private int id_blog;
    private String texte_reponse;

    private Date date_ajout_reponse;
    private int validation;

    public BlogReponse() 
    {
        
    }

    public BlogReponse(int id_question, int id_blog, String texte_reponse, Date date_ajout_reponse, int validation) {
        this.id_reponse_question = id_reponse_question;
        this.id_question = id_question;
        this.id_blog = id_blog;
        this.texte_reponse = texte_reponse;

        this.date_ajout_reponse = date_ajout_reponse;
        this.validation = validation;
    }

    public int getId_reponse_question() {
        return id_reponse_question;
    }

    public int getId_question() {
        return id_question;
    }

    public int getId_blog() {
        return id_blog;
    }

    public String getTexte_reponse() {
        return texte_reponse;
    }

    public Date getDate_ajout_reponse() {
        return date_ajout_reponse;
    }

    public int getValidation() {
        return validation;
    }

    public void setId_reponse_question(int id_reponse_question) {
        this.id_reponse_question = id_reponse_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public void setTexte_reponse(String texte_reponse) {
        this.texte_reponse = texte_reponse;
    }

    public void setDate_ajout_reponse(Date date_ajout_reponse) {
        this.date_ajout_reponse = date_ajout_reponse;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.id_reponse_question;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BlogReponse other = (BlogReponse) obj;
        if (this.id_reponse_question != other.id_reponse_question) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "reponse{" + "id_reponse_question=" + id_reponse_question + ", id_question=" + id_question + ", id_blog=" + id_blog + ", texte_reponse=" + texte_reponse + ", date_ajout_reponse=" + date_ajout_reponse + ", validation=" + validation + '}';
    }
}
