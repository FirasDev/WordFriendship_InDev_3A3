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
 * @author Marwen
 */
public class Blog 
{
    private int id_blog	;
    private int id_utilisateur;
   private String titre_blog	;
    private String article_blog;
   
   private Date date_ajout_blog ;

    public Blog(int id_utilisateur, String titre_blog, String article_blog, Date date_ajout_blog) {
        this.id_blog = id_blog;
        this.id_utilisateur = id_utilisateur;
        this.titre_blog = titre_blog;
        this.article_blog = article_blog;
       
        this.date_ajout_blog = date_ajout_blog;
    }

    public Blog() {
    }

    public int getId_blog() {
        return id_blog;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getTitre_blog() {
        return titre_blog;
    }

    public String getArticle_blog() {
        return article_blog;
    }

    

    public Date getDate_ajout_blog() {
        return date_ajout_blog;
    }

    public void setId_blog(int id_blog) {
        this.id_blog = id_blog;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setTitre_blog(String titre_blog) {
        this.titre_blog = titre_blog;
    }

    public void setArticle_blog(String article_blog) {
        this.article_blog = article_blog;
    }

   

    public void setDate_ajout_blog(Date date_ajout_blog) {
        this.date_ajout_blog = date_ajout_blog;
    }

    @Override
    public String toString() {
        return "blog{" + "id_blog=" + id_blog + ", id_utilisateur=" + id_utilisateur + ", titre_blog=" + titre_blog + ", article_blog=" + article_blog + ", date_ajout_blog=" + date_ajout_blog + '}';
    }

    @Override
    public int hashCode() {
        return  this.id_blog;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        return this.id_blog == other.id_blog;
    }
    
}
