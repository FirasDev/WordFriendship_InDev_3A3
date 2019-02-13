/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.BlogReponse;
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

public class BlogReponseCrud 
{
    
    Connection db;

    public BlogReponseCrud() throws SQLException 
    {
        db = MyDBcon.getInstance().getCon();

    }
  public void insert(BlogReponse t) 
  {
        
        try {
            // the mysql insert statement
            String query = " insert into reponse (id_question,id_blog,texte_reponse,validation) values (?,?,?,?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_question());
            preparedStmt.setInt(2,t.getId_blog());
            preparedStmt.setString(3,t.getTexte_reponse());
            preparedStmt.setInt(4,t.getValidation());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public List<BlogReponse> DisplayAll() 
    {
        List<BlogReponse> list = new ArrayList<>() ;
        try {
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM reponse";
            
            // create the java statement
            Statement st = db.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {   BlogReponse b = new BlogReponse();
                b.setId_reponse_question(rs.getInt("id_reponse_question"));
                b.setId_question(rs.getInt("id_question"));
                b.setId_blog(rs.getInt("id_blog"));
                b.setTexte_reponse(rs.getString("texte_reponse"));
                b.setValidation(rs.getInt("validation"));
               // b.setDate_ajout_reponse(rs.getDate("date_ajout_reponse")); 
               
                list.add(b);
                } } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delete(BlogReponse t) 
    {
        try {
            String query = "delete from reponse where id_reponse_question = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_reponse_question());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(BlogReponse t) 
    {
        
        try {
            // create the java mysql update preparedstatement
            String query = "update reponse set texte_reponse = ?  where id_reponse_question = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setString(1, t.getTexte_reponse());
            preparedStmt.setInt(2, t.getId_reponse_question());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
