
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
import java.util.HashMap;
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

        String query = "INSERT INTO `experience` (`Titre_exp`,`type_exp`,`desc_exp`,`date_exp`,`eval_exp`,`id_pays`,`image`,`id_user`) VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);

            ste.setString(1, experience.getTitre_exp());
            ste.setString(2, experience.getType_exp());
            ste.setString(3, experience.getDesc_exp());
            ste.setDate(4, experience.getDate_exp());
            ste.setFloat(5, experience.getEval_exp());
            ste.setInt(6, experience.getId_pays());
            ste.setString(7, experience.getImage());
            ste.setInt(8, experience.getId_user());
//            ste.setInt(6, experience.getId_pays());
//            ste.setInt(7, experience.getId_user());

//            ste.setInt(9, experience.getId_album_experience());
            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Experience> DisplayExperiences() throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        List MyExperiences = new ArrayList<>();

        String query = "SELECT e.*,p.name as name_country,u.username from `experience` e,`pays` p, `user` u WHERE e.id_experience = p.id AND u.id = e.id_user ";
        try {
            Statement ste = con.createStatement();
            ResultSet set = ste.executeQuery(query);

            while (set.next()) {

                Experience exp = new Experience(set.getInt("id_experience"), set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"),set.getInt("id_pays"),set.getString("name_country"),set.getInt("id_user"),set.getString("username"));      

                MyExperiences.add(exp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MyExperiences;

    }
    
        public static List<Experience> DisplayExperiencesById(int id) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        List MyExperiences = new ArrayList<>();

        String query = "SELECT e.*,p.name as name_country,u.username from `experience` e,`pays` p, `user` u WHERE e.id_experience = p.id AND e.id_user=? group by id_experience";
        try {
			PreparedStatement ste = con.prepareStatement(query);
			ste.setInt(1, id);
			ResultSet set = ste.executeQuery();

            while (set.next()) {

                Experience exp = new Experience(set.getInt("id_experience"), set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"),set.getInt("id_pays"),set.getString("name_country"),set.getInt("id_user"),set.getString("username"));      

                MyExperiences.add(exp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return MyExperiences;

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

        String query = "SELECT id from `pays` WHERE `name` ='" + pays + "'";
        try {

            Statement ste = con.createStatement();
            ResultSet result = ste.executeQuery(query);
            if (result.next()) {
                return result.getInt("id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }
        // fonction de recherche pays selon le id du pays
        public static String SearchPaysByName(int pays) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();

        String query = "SELECT name from `pays` WHERE `id` ='" + pays + "'";
        try {

            Statement ste = con.createStatement();
            ResultSet result = ste.executeQuery(query);
            if (result.next()) {
                return result.getString("name");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }


    
    //fonction de recherche experience selon nom du pays
    public static List<Experience> RechercherSelonPays(String pays) throws SQLException {
        List<Experience> MyExperiences = new ArrayList<Experience>();
        Connection con = MyDBcon.getInstance().getCon();
        Statement ste = con.createStatement();
        int idpays = SearchPays(pays);
        if (idpays == 0) {
            return null;
        } else {
            String query = "SELECT * from `experience`,`pays` WHERE id_pays= " + idpays;
            try {

                ResultSet set = ste.executeQuery(query);
                while (set.next()) {

                    Experience exp = new Experience(set.getString("Titre_exp"), set.getString("type_exp"), set.getString("desc_exp"), set.getDate("date_exp"), set.getFloat("eval_exp"), set.getInt("id_pays"));

                    MyExperiences.add(exp);
                    return MyExperiences;
                }
                return MyExperiences;

            } catch (SQLException ex) {
                Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
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

    public static List<Experience> ReturnFavExperience(User user, char c) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "Select e.* from `experience` e `fav_experience` f WHERE id_user = f.id_user and f.id_experience = e.id_experience ";

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

    public static void InsertImageExperience(String url, int id) throws SQLException {

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

    public static boolean VerifExperienceLocation(Experience experience, String pays) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();

        long x = RechercherSelonPays(pays).stream().count();
        if (x > 0) {
            return true;
        }

        return false;

    }

    public static void DeleteImageExperience(int id, int id_image) throws SQLException {

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
    public static void UserRatingExperience(int rating, Experience experience, int id_user) throws SQLException {

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

    public static void InsertCommentExperience(String comment, Experience experience, int id_user) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "INSERT INTO `comm_exp` (`comment`,`id_exp`,`id_user`) VALUES(?,?,?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);

            ste.setString(1, comment);
            ste.setInt(2, experience.getId_experience());
            ste.setInt(3, id_user);

            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void DeleteCommentExperience(Experience experience, int id_user) throws SQLException {

        Connection con = MyDBcon.getInstance().getCon();

        String query = "Delete from `comm_exp` WHERE (`id_user` = ? AND `id_exp` = ?)";

        try {
            PreparedStatement ste = con.prepareStatement(query);

            ste.setInt(1, id_user);
            ste.setInt(2, experience.getId_experience());

            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //fonction mise a jour des string de table experience
    public static void updateExp(Experience experience, int id) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        String strSQLQuery = String.format("UPDATE `experience` SET `Titre_exp`=?,`type_exp`=?,`desc_exp`=?,`date_exp`=?,`eval_exp`=? WHERE id_experience='" + id + "'");
        System.out.println(strSQLQuery);
        try {
            PreparedStatement ste = con.prepareStatement(strSQLQuery);
            ste.setString(1, experience.getTitre_exp());
            ste.setString(2, experience.getType_exp());
            ste.setString(3, experience.getDesc_exp());
            ste.setDate(4, experience.getDate_exp());
            ste.setFloat(5, experience.getEval_exp());

            ste.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Experience.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    	public static HashMap<String, Integer> GetNameIdMap() throws SQLException {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection con = MyDBcon.getInstance().getCon();
		String query = "SELECT id_experience,titre_exp from experience";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				map.put(set.getString("titre_exp"), set.getInt("id_experience"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
        
	public static List<String> GetNamelist() throws SQLException {

		List<String> list = new ArrayList<>();
		Connection con = MyDBcon.getInstance().getCon();
		String query = "SELECT titre_exp from experience";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				list.add(set.getString("titre_exp"));
				// System.out.println(list);
			}
			return list;
		} catch (SQLException ex) {
			Logger.getLogger(ExperienceCrud.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}       
}
