/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordfriendship_desktop;

import Entities.Blog;
import Entities.BlogCommentaire;
import Services.BlogCrud;
import Entities.BlogQuestion;
import Services.BlogQuestionCrud;
import Entities.BlogReponse;
import Services.BlogCommentaireCrud;
import Services.BlogQuestionCrud;
import Services.BlogReponseCrud;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Marwen
 */
public class Wordfriendship_desktop {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException 
    {
        // ------------------- Blog -------------------
        
        /*
        // TODO code application logic here
         Blog b = new Blog(0,"Titre","Article",new Date(1970-1-1));
         Blog x = new Blog(5,"Titre5","Article5",new Date(1970-1-1));

        //b.setId_utilisateur(1);
        //b.setTitre_blog("DDDDD");
        //b.setArticle_blog("eeeee");
        
        BlogCrud bc = new BlogCrud();
        
        bc.insert(b);
        bc.delete(b);
        
        List<Blog> list_blogs = bc.DisplayAll();
        
        System.out.println(list_blogs);
                
        Blog blog_update = new Blog();
        
        blog_update.setId_blog(4);
        blog_update.setTitre_blog("CCCCC");
        
        bc.update(blog_update); */
        
        
        // ------------------- Questions -------------------
       
        /*
        BlogQuestion b = new BlogQuestion(2,7,"yallaaa",new Date(2019-02-13));
        BlogQuestion a = new BlogQuestion(2,7,"goooooo",new Date(2019-02-13));

        BlogQuestionCrud bq = new BlogQuestionCrud();
      
        //bq.insert(b);
        //bq.insert(a);
       
        //b.setId_question(1);
        //bq.delete(b);
        
        List<BlogQuestion> list_blogs = bq.DisplayAll();
        System.out.println(list_blogs);
        
        BlogQuestion blog_update = new BlogQuestion();
        
        blog_update.setId_question(3);
        blog_update.setTexte_question("Parfait ");
        
        bq.update(blog_update);  */
        
        // ------------------- Reponses -------------------
        
        /*
        BlogReponse a = new BlogReponse(2,7,"yallaaa",new Date(2019-02-13),0);
        BlogReponse b = new BlogReponse(2,7,"goooooo",new Date(2019-02-13),1);

        BlogReponseCrud br = new BlogReponseCrud();
      
        //br.insert(a);
        //br.insert(b);
        
        //b.setId_reponse_question(3);
        //br.delete(b);
        
        List<BlogReponse> list_blogs = br.DisplayAll();
        System.out.println(list_blogs);
        
        BlogReponse blog_update = new BlogReponse();
        
        blog_update.setId_reponse_question(2);
        blog_update.setTexte_reponse("Parfait ");
        
        br.update(blog_update); */
        
        // ------------------- Commentaires -------------------

        /*
        BlogCommentaire a = new BlogCommentaire(2,new Date(2019-02-13),"j'ai bien aimé cette publication !");
        BlogCommentaire b = new BlogCommentaire(2,new Date(2019-02-13),"j'ai pas aimé cette publication !");
        
        BlogCommentaireCrud bcm = new BlogCommentaireCrud();
      
        //bcm.insert(a);
        //bcm.insert(b);
        
        //b.setId_commentaire(2);
        //bcm.delete(b);
        
        List<BlogCommentaire> list_blogs = bcm.DisplayAll();
        System.out.println(list_blogs);
        
        BlogCommentaire blog_update = new BlogCommentaire();
        
        blog_update.setId_commentaire(8);
        blog_update.setTexte_commentaire("Parfait ");
        
        bcm.update(blog_update); */
        
    }
    
}
