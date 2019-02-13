/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.SQLException;
import Entities.Experience;
import Entities.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas
 */
public class ExperienceCrud {
    
    public static void InsertExperience(Experience experience) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "INSERT INTO `experience` (`Titre_exp`,`type_exp`,`desc_exp`,`date_exp`,`eval_exp`,`id_pays`) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setString(1, experience.getTitre_exp());
            ste.setString(2, experience.getType_exp());
            ste.setString(3, experience.getDesc_exp());
            ste.setDate(4, experience.getDate_exp());
            ste.setFloat(5, experience.getEval_exp());
            ste.setInt(6, experience.getId_pays());
            
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     

    public static List<Experience> DisplayExperiences() throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        List MyExperiences = new ArrayList<>();

            String query = "SELECT * from `experience` ORDER BY 'eval_ex'";
        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);

            while (set.next()) {

                Experience exp = new Experience(set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"), set.getInt("id_pays"));

                MyExperiences.add(exp);
                return MyExperiences;
            }

            System.out.println(MyExperiences);
        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public static void RemoveExperience(int id) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();

        String query = "DELETE FROM `experience` where id_experience=? ";

        try {
            PreparedStatement ste = con.prepareStatement(query);

            ste.setInt(1, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // fonction de recherche pays selon le nom du pays
    public static int SearchPays(String pays) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();

        String query = "SELECT id from `country` WHERE 'name_pays' ==" + pays;
        try{
            
        Statement ste = con.createStatement();
        ResultSet result = ste.executeQuery(query);

        return result.getInt("id_pays");
        
        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;

    }

    //fonction de recherche experience selon nom du pays
    public static List<Experience> RechercherSelonPays(String pays) throws SQLException {
        List<Experience> MyExperiences = new ArrayList<Experience>();
        Connection con = MyDBcon.getInstance().getCon();
        Statement ste = con.createStatement();
        int idpays = SearchPays(pays);
        if (idpays == 0) {
            return null;
        }else {
        String query = "SELECT * from `experience`,`country` WHERE id_pays= " + idpays;
        try{
            
        ResultSet set = ste.executeQuery(query);
        while (set.next()) {

            Experience exp = new Experience(set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"), set.getInt("id_pays"));

            MyExperiences.add(exp);
            return MyExperiences;
        }
        return MyExperiences;
        
        
        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
        }
    }

    //fonction mise a jour des string de table experience
    public static void update(String row, String value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `experience` set %s = '%s' WHERE id_experience=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

    //fonction mise a jour des int de table experience
	public static void update(String row, int value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `experience` set %s = '%s' WHERE id_experience=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
        
        //fonction mise a jour des floats de table experience
        public static void update(String row, float value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `experience` set %s = '%s' WHERE id_experience=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {
			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

        //fonction mise a jour des dates de table experience
	public static void update(String row, Date value, int id) throws SQLException {
		Connection con = MyDBcon.getInstance().getCon();
		String strSQLQuery = String.format("UPDATE `experience` set %s = '%s' WHERE id_experience=%s", row, value, id);
		System.out.println(strSQLQuery);
		try {

			PreparedStatement ste = con.prepareStatement(strSQLQuery);
			ste.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
        
        public static List<Experience> ReturnFavExperience(User user, char c) throws SQLException{
            
            Connection con = MyDBcon.getInstance().getCon();
            
            String query ="Select e.* from `experience` e `fav_experience` f WHERE id_user = f.id_user and f.id_experience = e.id_experience ";
            
            
            List MyExperiences = new ArrayList<>();

        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);

            while (set.next()) {

                Experience exp = new Experience(set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"), set.getInt("id_pays"));

                MyExperiences.add(exp);
                return MyExperiences;
            }

            System.out.println(MyExperiences);
        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        }
        
     public static void InsertImageExperience(String url,int id) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "INSERT INTO `album_experience` (`Titre_exp`,`id_experience`) VALUES(?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setString(1, url);
            ste.setInt(2, id);
            
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
     public static void DeleteImageExperience(int id,int id_image) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "DELETE FROM `album_experience` where id_experience=? AND id_album_experience=?";

        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setInt(1, id);
            ste.setInt(2, id_image);
            
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
//     public static void ReturnImageExperience(String url,Experience experience) throws SQLException {
//
//        Connection con = MyDBcon.getInstance().getCon();
//
//        String query = "INSERT INTO `album_experience` (`Titre_exp`,`id_experience`) VALUES(?,?)";
//
//        try {
//            PreparedStatement ste = con.prepareStatement(query);
//            
//            ste.setString(1, url);
//            ste.setInt(2, experience.getId_experience());
//            
//            ste.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
     
     public static void UserRatingExperience(int rating,Experience experience,int id_user) throws SQLException{
         
         Connection con = MyDBcon.getInstance().getCon();

        String query = "INSERT INTO `user_rating_exp` (`rating`,`id_user`,`id_experience`) VALUES(?,?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);
            
            ste.setInt(1, rating);
            ste.setInt(2, id_user);
            ste.setInt(3, experience.getId_experience());
            
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
}
