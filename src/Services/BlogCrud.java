/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import Entities.Blog;
import java.sql.Connection;
import java.sql.Date;
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
public class BlogCrud implements BlogIservice<Blog>
{
    
    Connection db;

    public BlogCrud() throws SQLException {
        db = MyDBcon.getInstance().getCon();

    }
    
    @Override
    public void insert(Blog t) {
        
        try {
            // the mysql insert statement
            String query = " insert into blog (id_utilisateur,titre_blog,article_blog) values (?,?,?)";
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_utilisateur());
            preparedStmt.setString (2,t.getTitre_blog() );
            preparedStmt.setString   (3,t.getArticle_blog() );
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public List<Blog> DisplayAll() 
    {
        List<Blog> list = new ArrayList<>() ;
        try {
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM blog";
            
            // create the java statement
            Statement st = db.createStatement();
            
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            
            // iterate through the java resultset
            while (rs.next())
            {   Blog b = new Blog();
                b.setId_blog(rs.getInt("id_blog"));
                b.setId_utilisateur( rs.getInt("id_utilisateur"));
                b.setTitre_blog( rs.getString("titre_blog"));
                /*b.setDate_ajout_blog(rs.getDate("date_ajout_blog")); */
                b.setArticle_blog( rs.getString("article_blog"));
               
                list.add(b);
                } } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delete(Blog t) 
    {
        try {
            String query = "delete from blog where id_blog = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getId_blog());
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Blog t) 
    {
        
        try {
            // create the java mysql update preparedstatement
            String query = "update blog set titre_blog = ?  where id_blog = ?";
            PreparedStatement preparedStmt = db.prepareStatement(query);
            preparedStmt.setString(1, t.getTitre_blog());
            preparedStmt.setInt(2, t.getId_blog());
            
            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    
}
