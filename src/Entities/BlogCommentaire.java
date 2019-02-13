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
public class BlogCommentaire 
{
    private int id_commentaire;
    private int id_utilisateur;
    private Date date_commentaire;
    private String texte_commentaire;

    public BlogCommentaire() 
    {
        
    }

    public BlogCommentaire(int id_utilisateur, Date date_commentaire, String texte_commentaire) 
    {
        this.id_commentaire = id_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.date_commentaire = date_commentaire;
        this.texte_commentaire = texte_commentaire;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public String getTexte_commentaire() {
        return texte_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    public void setTexte_commentaire(String texte_commentaire) {
        this.texte_commentaire = texte_commentaire;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_commentaire;
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
        final BlogCommentaire other = (BlogCommentaire) obj;
        if (this.id_commentaire != other.id_commentaire) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id_commentaire=" + id_commentaire + ", id_utilisateur=" + id_utilisateur + ", date_commentaire=" + date_commentaire + ", texte_commentaire=" + texte_commentaire + '}';
    }
}
