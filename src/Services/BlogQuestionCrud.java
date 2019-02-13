/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.BlogQuestion;
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
public class BlogQuestionCrud 
{
 Connection db;

    public BlogQuestionCrud() throws SQLException 
    {
        db = MyDBcon.getInstance().getCon();

    }
    
    public void insert(BlogQuestion t) 
    {
        
        try {
            // the mysql insert statement
            String query = " insert into questions (id_utilisateur,id_blog,texte_question) values (?,?,?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_utilisateur());
            preparedStmt.setInt(2,t.getId_blog());
            preparedStmt.setString(3,t.getTexte_question());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public List<BlogQuestion> DisplayAll() 
    {
        List<BlogQuestion> list = new ArrayList<>() ;
        try {
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM questions";
            
            // create the java statement
            Statement st = db.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {   BlogQuestion b = new BlogQuestion();
                b.setId_question(rs.getInt("id_question"));
                b.setId_blog(rs.getInt("id_blog"));
                b.setId_utilisateur( rs.getInt("id_utilisateur"));
                b.setTexte_question(rs.getString("texte_question"));
               // b.setDate_ajout_question(rs.getDate("date_ajout_blog")); 
               
                list.add(b);
                } } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void delete(BlogQuestion t) 
    {
        try {
            String query = "delete from questions where id_question = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_question());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(BlogQuestion t) 
    {
        
        try {
            // create the java mysql update preparedstatement
            String query = "update questions set texte_question = ?  where id_question = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setString(1, t.getTexte_question());
            preparedStmt.setInt(2, t.getId_question());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
     }   
}
