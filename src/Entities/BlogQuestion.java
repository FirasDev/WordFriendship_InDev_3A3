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
 */
public class BlogQuestion 
{
    private int id_question;
    private int id_utilisateur;
    private int id_blog;
    private String texte_question;

    private Date date_ajout_question;

    public BlogQuestion() 
    {
        
    }

    public BlogQuestion(int id_utilisateur, int id_blog, String texte_question, Date date_ajout_question) {
        this.id_question = id_question;
        this.id_utilisateur = id_utilisateur;
        this.id_blog = id_blog;
        this.texte_question = texte_question;
        
        this.date_ajout_question = date_ajout_question;
    }

    public int getId_question() {
        return id_question;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public int getId_blog() {
        return id_blog;
    }

    public String getTexte_question() {
        return texte_question;
    }
    

    public Date getDate_ajout_question() {
        return date_ajout_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public void setTexte_question(String texte_question) {
        this.texte_question = texte_question;
    }


    public void setDate_ajout_question(Date date_ajout_question) {
        this.date_ajout_question = date_ajout_question;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_question;
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
        final BlogQuestion other = (BlogQuestion) obj;
        return this.id_question == other.id_question;
    }

    
    
    @Override
    public String toString() {
        return "question{" + "id_question=" + id_question + ", id_utilisateur=" + id_utilisateur + ", id_blog=" + id_blog + ", texte_question=" + texte_question +  ", date_ajout_question=" + date_ajout_question + '}';
    }
}
