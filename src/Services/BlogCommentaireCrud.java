/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.BlogCommentaire;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marwen
 */
public class BlogCommentaireCrud 
{
    
    Connection db;

    public BlogCommentaireCrud() throws SQLException 
    {
        db = MyDBcon.getInstance().getCon();

    }
  public void insert(BlogCommentaire t) 
  {
        
        try {
            // the mysql insert statement
            String query = " insert into commentaire (id_utilisateur,texte_commentaire) values (?,?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_utilisateur());
            preparedStmt.setString(2,t.getTexte_commentaire());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public List<BlogCommentaire> DisplayAll() 
    {
        List<BlogCommentaire> list = new ArrayList<>() ;
        try {
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM commentaire";
            
            // create the java statement
            Statement st = db.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {   BlogCommentaire b = new BlogCommentaire();
                b.setId_commentaire(rs.getInt("id_commentaire"));
                b.setId_utilisateur(rs.getInt("id_utilisateur"));
                b.setDate_commentaire(rs.getDate("date_commentaire"));
                b.setTexte_commentaire(rs.getString("texte_commentaire"));
               
                list.add(b);
                } } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delete(BlogCommentaire t) 
    {
        try {
            String query = "delete from commentaire where id_commentaire = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_commentaire());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(BlogCommentaire t) 
    {
        
        try {
            // create the java mysql update preparedstatement
            String query = "update commentaire set texte_commentaire = ?  where id_commentaire = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setString(1, t.getTexte_commentaire());
            preparedStmt.setInt(2, t.getId_commentaire());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
