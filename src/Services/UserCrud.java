/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import static Controllers.FXMLajouterUserController.showAlert;
import Entities.Grade;
import Entities.Sessions;
import Entities.User;
import static Services.AdminService.cnx;
import Utils.BCrypt;
import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Firas
 */
public class UserCrud {

    static Connection cnx;

    public UserCrud() throws SQLException {
        cnx = MyDBcon.getInstance().getCon();

    }
    
     public static int getCode_confir() { //pour le mail
        String query = "select `code` from User ";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                return set.getInt("code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    public static int rondomcodeconf()      
   {      int codeconf=-1;
      
       codeconf = (new Random().nextInt(9999-1000)+1000);
       System.out.println("rondom" + codeconf);
      
      return codeconf;
   }
    
     public static int getCode_confir(String email) { //pour le mail
        String query = "select * from User where email = '" + email + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                return set.getInt("code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    

    public void loggin(User u, int id) {
        String req = "UPDATE user SET enabled=0 WHERE (id=? AND enabled=1)";
        try {

            PreparedStatement prepared = cnx.prepareStatement(req);
            prepared.setInt(1, id);
            prepared.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aotorisation(int id) { //pour la connexion la premiere fois

        String req = "UPDATE user SET confirmation_token='oui' WHERE (id=?)";
        try {

            PreparedStatement prepared = cnx.prepareStatement(req);
            prepared.setInt(1, id);
            prepared.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Boolean CheckUserEmail(String email) throws SQLException { //pour le login
        try {
            Statement stmm = cnx.createStatement();
            String req = "SELECT `email` FROM `user` WHERE email = '" + email + "'";
            ResultSet rsm = stmm.executeQuery(req);
            if (rsm.next()) {
                System.out.println("existant");
                return true;
            } else {
                System.out.println("non existant");
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;

    }

    public static Boolean CheckUserPassword(String email, String password) {  //pour le login

        String query = "select password from user where email = '" + email + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            if (set.next()) {
                return BCrypt.checkpw(password, set.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Boolean getCodeconfirmation(int id) { //pour le login le premiere fois
        String query = "select * from User where id = '1'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int getId_Email(String email) {
        String query = "select * from User where email = '" + email + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                return set.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static String getConfirmation_token(String email) {
        String query = "select * from User where email = '" + email + "'";
        try {
            Statement ste = cnx.createStatement();
            ResultSet set = ste.executeQuery(query);
            while (set.next()) {
                return set.getString("confirmation_token");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int VerificationUtilisateur(User u) {

        int roles = -1;
        String req = "select id,email,roles from user where email=(?)";
        try {
            PreparedStatement prepared = cnx.prepareStatement(req);
            prepared.setString(1, u.getEmail());
            //prepared.setString(2, p);

            ResultSet resultat = prepared.executeQuery();

            while (resultat.next()) {
                u.setId(resultat.getInt(1));
                u.setEmail(resultat.getString(2));
                u.setRoles(resultat.getString(3));
                System.out.println("verif role" + u.getRoles());
                System.out.println("verif id" + u.getId());
                
                String r = u.getRoles();
                System.out.println("verif role" + r);

            }
           
            if (u.getRoles().equalsIgnoreCase("ADMIN")) {
                roles = 0;
            
            } else if ((u.getRoles().equalsIgnoreCase("ABONNEE"))) {
            roles = 1;
             } 

        } catch (SQLException ex) {

        }

        return roles;
    }

    public void Deconnexion(int id) {

        try {
            String req = "UPDATE user SET enabled= 1 WHERE id=?";

            PreparedStatement ste = cnx.prepareStatement(req);

            ste.setInt(1, id);
            ste.executeUpdate();

        } catch (SQLException ex) {
            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    

    public static void ajouterUser(String email, User u, Grade g) {

        try {
            Statement stmm = cnx.createStatement();
            String reqm = "SELECT `email` FROM `user` WHERE email = '" + email + "' ";
            ResultSet rsm = stmm.executeQuery(reqm);
            if (rsm.next()) {
                System.out.println("existant");
            } else {
                String req = "INSERT INTO `user`(`username`, `email`,`password`,`roles`,`firstname`,`lastname`,`nationalite`,`langues`,`date_naissance`,`photo_p`,`descriptions`,`sexe`,`code`,`tel`) VALUES (?,?,?,'ABONNEE',?,?,?,?,?,?,?,?,'"+rondomcodeconf()+"',?)";
                PreparedStatement pstm = cnx.prepareStatement(req);
                pstm.setString(1, u.getUsername());
                pstm.setString(2, u.getEmail());

                pstm.setString(3, BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12)));

                // pstm.setString(3, u.getPassword());
                pstm.setString(4, u.getFirstname());
                pstm.setString(5, u.getLastname());
                pstm.setString(6, u.getNationalite());
                pstm.setString(7, u.getLangues());

                pstm.setString(8, u.getDate_naissance());
                pstm.setString(9, u.getPhoto_p());
                pstm.setString(10, u.getDescriptions());
                pstm.setString(11, u.getSexe());
                pstm.setInt(12, u.getTel()); 

                int id_user = u.getId();
                pstm.executeUpdate();
                //+++++++++

                Statement stm = cnx.createStatement();
                String req3 = "SELECT MAX(id) from user";

                ResultSet resultat = stm.executeQuery(req3);
                while (resultat.next()) {
                    int id_p = resultat.getInt(1);

                    //+++++++++++++
                    String req4 = "INSERT INTO `grade`( id_ug,`grade`) VALUES (?,?)";
                    PreparedStatement pstm4 = cnx.prepareStatement(req4);
                    pstm4.setInt(1, id_p);
                    pstm4.setString(2, g.getGrade());
                    pstm4.executeUpdate();
                }
                System.out.println("non existant");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void desactiverUser(int id, String pass) {
        try {
            Statement stmm = cnx.createStatement();
            String reqm = "SELECT `password` FROM `user` WHERE password = '" + pass + "' and id=" + id + " ";
            ResultSet rsm = stmm.executeQuery(reqm);
            if (rsm.next()) {

                String req = "update user set enabled=0 where id=?";
                PreparedStatement pstm = cnx.prepareStatement(req);
                pstm.setInt(1, id);
                pstm.executeUpdate();

            } else {
                showAlert(Alert.AlertType.ERROR, "Données erronés", "Verifier les données", "password incorrect !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void modifiermdp(int id, String password) {
        try {
            String req = "update user set password= '" + password + "' where id=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setInt(1, id);

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void modifierUser(int id, String username, String email,
            String firstname, String lastname, String nationalite, String langues, String date_naissance, String photo_p, String descriptions, String sexe) {
        try {
            String req = "update user set username=?,email=?,firstname=?,lastname=?,nationalite=?,langues=?,date_naissance=?,photo_p=?,descriptions=?,sexe=? where id=?";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setString(1, username);
            pstm.setString(2, email);

            pstm.setString(3, firstname);
            pstm.setString(4, lastname);
            pstm.setString(5, nationalite);
            pstm.setString(6, langues);
            pstm.setString(7, date_naissance);
            pstm.setString(8, photo_p);
            pstm.setString(9, descriptions);
            pstm.setString(10, sexe);
            pstm.setInt(11, id);

            pstm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*public static  HashMap<User,Grade> afficheProfil() throws SQLException {//afficher liste des profils
            HashMap<User,Grade> matchingTypes = new HashMap<>();
         
            
                 String req2 = "SELECT user.username,user.email,user.roles,user.firstname,user.lastname,user.nationalite,user.langues,\n" +
                               " grade.grade,user.date_naissance,user.photo_p,user.descriptions FROM user,grade where grade.id_ug=user.id";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2);               
                 ResultSet rs= pstm2.executeQuery(req2); 
 
                 while (rs.next() )
                 {     
            String date_naissance = rs.getString("date_naissance");
            String photo_p = rs.getString("photo_p");
            String descriptions = rs.getString("descriptions");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String nationalite = rs.getString("nationalite");
            String langues = rs.getString("langues");
            String grade = rs.getString("grade");
                    
            matchingTypes.put(new User( date_naissance, photo_p, descriptions,username, email, roles, firstname, lastname, nationalite, langues),new Grade(grade));
            
            }
                    
            return matchingTypes;
        }*/
 /*public static  HashMap<User,Grade> afficheProfilU(int id) throws SQLException {//afficher un profil
            HashMap<User,Grade> matchingTypes = new HashMap<>();
         
            
                 String req2 = "SELECT user.username,user.email,user.roles,user.firstname,user.lastname,user.nationalite,user.langues,\n" +
                               " grade.grade,user.date_naissance,user.photo_p,user.descriptions FROM user,grade where user.id='" + id + "' and grade.id_ug='" + id + "'";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                 
                 ResultSet rs= pstm2.executeQuery(req2);   
                  
              
                 while (rs.next() )
                 {     
            String date_naissance = rs.getString("date_naissance");
            String photo_p = rs.getString("photo_p");
            String descriptions = rs.getString("descriptions");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String roles = rs.getString("roles");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            String nationalite = rs.getString("nationalite");
            String langues = rs.getString("langues");
            String grade = rs.getString("grade");
                    
            matchingTypes.put(new User(date_naissance, photo_p, descriptions,username, email, roles, firstname, lastname, nationalite, langues),new Grade(grade));
            
            }
                    
            return matchingTypes;
            
            
}*/
    public User getUserById(int id) {
        User u = null;
        try {
            String req = "SELECT * FROM `user` WHERE id = '" + id + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            if (rs.first()) {
                u = new User(id, rs.getString("username"), rs.getString("email"), rs.getBoolean("enabled"), rs.getString("roles"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("nationalite"), rs.getString("langues"), rs.getString("date_naissance"), rs.getString("photo_p"), rs.getString("descriptions"));
                System.out.println("User retrieved");
            } else {
                System.out.println("Echec get User");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class
                    .getName()).log(Level.SEVERE, null, ex);

            return u;
        }
        return u;
    }

    public List<User> getAllUsers() throws SQLException {

        List<User> presonnes = new ArrayList<>();
        String req = "select id,username,email from user";
        Statement stm = cnx.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
            User p = new User(rst.getInt("id"),
                    rst.getString("username"),
                    rst.getString("email"));
            presonnes.add(p);
        }
        return presonnes;
    }

    public static ArrayList<Grade> getGrade() throws SQLException {
        ArrayList<Grade> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT grade FROM grade";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            String grade = resultat.getString("grade");
            retour.add(new Grade(grade));

        }

        return retour;
    }

    public static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM user";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            String username = resultat.getString("username");
            String email = resultat.getString("email");
            String firstname = resultat.getString("firstname");
            String lastname = resultat.getString("lastname");
            String nationalite = resultat.getString("nationalite");
            String langues = resultat.getString("langues");
            String photo_p = resultat.getString("photo_p");
            String descriptions = resultat.getString("descriptions");
            retour.add(new User(username, email, firstname, lastname, nationalite, langues, photo_p, descriptions));

        }

        return retour;
    }

    public static ArrayList<User> getAmisU(int id) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        
        //String req = "select user.firstname,user.lastname from user,amis where (amis.id_ue='"+id+"' or amis.id_ur='"+id+"' and etat_a=1))";
        String req = "select user.id, user.firstname,user.lastname from user,amis where ((user.id!='"+id+"' and amis.id_ur=user.id ) or (user.id!='"+id+"' and amis.id_ue=user.id))";
        ps = cnx.prepareStatement(req);
       
        rs = ps.executeQuery();
        while (rs.next()) {

            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            System.out.println(lastname);
            int idd = rs.getInt("user.id");
            retour.add(new User(idd,firstname, lastname));

        }
        

      /*  String query2 = "select user.firstname,user.lastname from user,amis where amis.id_ue=user.id and etat_a=1 and amis.id_ur=?";
        ps = cnx.prepareStatement(query2);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        while (rs.next()) {
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            retour.add(new User(firstname, lastname));
        }*/
        return retour;
    }
    
       public static  ArrayList<User> chercherAmis() throws SQLException {//afficher liste des abonnés
            ArrayList<User> matchingTypes = new ArrayList<>();
         
            
                 String req2 = "SELECT id, `username`, `firstname`, `lastname` FROM user ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2);               
                 ResultSet rs= pstm2.executeQuery(req2); 
 
                 while (rs.next() )
                 {     
            int id=rs.getInt("id");
            String username = rs.getString("username");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
           
                    
            matchingTypes.add(new User(id,username,firstname,lastname));
                 
            }
                    
            return matchingTypes;
        }
    
    
        public static TreeMap<String,Integer> GetNameIdMap() throws SQLException{
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		Connection con = MyDBcon.getInstance().getCon();
		String query = "SELECT id,username from user order by id";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				map.put(set.getString("username"), set.getInt("id"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(PaysCrud.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}

}
