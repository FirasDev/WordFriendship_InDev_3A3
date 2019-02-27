/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Utils.MyDBcon;
import Entities.Cite;
import Entities.Logement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DongFeng
 */
public class LogementCrud {
    
     Connection cnx;
    
    public LogementCrud() throws SQLException {
        
       cnx = MyDBcon.getInstance().getCon();
          
    }
    
    ////////////////////////////////// ajouterrrrrrrrr /////////////
    
    public static void Ajouter_logement(Logement l,int id,int id_user) throws SQLException { 
      Connection cnx = MyDBcon.getInstance().getCon();
        if (Verifprix(l.getPrix())&&Verifnbrplace(l.getNbr_place()))
            
        {
            String req2 = "INSERT INTO `logement`(`nom`, `description`, `prix`, `nbr_place`, `id_cite`, `id_user`,`URL`) VALUES (?,?,?,?,?,?,?)";
             PreparedStatement pstm2 = cnx.prepareStatement(req2);
             pstm2.setString(1, l.getNom());            
             pstm2.setString(2, l.getDescription());            
             pstm2.setInt(3, l.getPrix());            
             pstm2.setInt(4, l.getNbr_place());            
             pstm2.setInt(5, id);            
             pstm2.setInt(6, id_user);            
             pstm2.setString(7,l.getURL());           
             pstm2.executeUpdate();
        }
        else 
            System.out.println("veuillez verifier vos donnes");
    }
    
    //////////////////afficherr logemeentttss /////////////////////
    
    public static ArrayList<Logement> Afficher_logement () throws SQLException {
        ArrayList<Logement> retour = new ArrayList<>();
        Connection cnx = MyDBcon.getInstance().getCon();
        Statement stm = cnx.createStatement();
        String req = "SELECT `nom`, `description`, `prix`,`nbr_place` FROM logement ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            String nom = resultat.getString(1);
            String descrition = resultat.getString(2);
            int prix = resultat.getInt(3);
            int nbr_place = resultat.getInt(4);
            retour.add(new Logement(nom, descrition, prix, nbr_place));
        }
        return retour;
    }
    
    
    
    //////////////////modifier un logementtt ALLLLLLL /////////////////////
    
    
  /* public void Modifier_logement (int num_villa,String nom,String description,int prix,int nbr_place) {

        try {              
                         
             String req1 = "SELECT id FROM logement INNER JOIN cite WHERE cite.num_villa= '"+num_villa+"' ";
             PreparedStatement pstm1 = cnx.prepareStatement(req1);
             ResultSet rs=pstm1.executeQuery(req1);    
             
         if (rs.next())
         {
            int id=rs.getInt(1);
             String req2 = "UPDATE `logement` SET `nom`=?,`description`=?,`prix`=?,`nbr_place`=? WHERE id='"+id+"' ";
             PreparedStatement pstm2 = cnx.prepareStatement(req2);
             pstm2.setString(1, nom);            
             pstm2.setString(2, description);         
             pstm2.setInt(3, prix);            
             pstm2.setInt(4, nbr_place);                       
             pstm2.executeUpdate();
         }                         
         } catch (SQLException ex) {
             Logger.getLogger(CiteCrud.class.getName()).log(Level.SEVERE, null, ex); 
         }
    } */
 
    /////////////////////////////////// CHERCHEEEEEEEERR LOGEMEEEENTTTTT + CITEEEEEE ///////////////////////////////////////   
        public static HashMap<Logement,Cite> chercher_log(String nom_cite) throws SQLException {
            HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            Connection cnx = MyDBcon.getInstance().getCon();
            {
                 String req2 = "SELECT logement.nom, `description`, `prix`, `nbr_place`,cite.nom, `num_villa` FROM `logement` inner join cite where cite.nom='"+nom_cite+"' and logement.id_cite=cite.id_cite ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {     
                String nom = rs.getString(1);
                String description = rs.getString(2);
                int prix = rs.getInt(3);
                int nbr_place = rs.getInt(4);           
                String nom_c=rs.getString(5);               
                int nbr_p=rs.getInt(6);
                matchingTypes.put(new Logement(nom, description, prix, nbr_place),new Cite(nom_c,nbr_p));          
            }}
            return matchingTypes;
        }
     
               
 
             
  /////////////////// veriificationnnnn priiiiix >0 apeeel fil ajout////////////////////////////////
public static boolean Verifprix (int prix)         
{
           if(prix>0) 
           {
               return true;
           }
               return false;
        }

 /////////////////// veriificationnnnn nbrr_placeeeeee >0 apeeel fil ajout////////////////////////////////

public static boolean Verifnbrplace (int nbr)         
{
           if(nbr>0) 
           {
               return true;
           }
               return false;
        }

/////////////////////////////////////afffichaaagee du logemeeenttt compleeet ////////////////////
  /*public HashMap<Logement,Cite> Affiche_complet() throws SQLException {
            HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            {
                 String req2 = "SELECT logement.nom, `description`, `prix`, `nbr_place`,cite.nom, `num_villa` FROM `logement` inner join cite where logement.id_cite=cite.id_cite ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {     
                String nom = rs.getString(1);
                String description = rs.getString(2);
                int prix = rs.getInt(3);
                int nbr_place = rs.getInt(4);           
                String nom_c=rs.getString(5);               
                int nbr_p=rs.getInt(6);
                matchingTypes.put(new Logement(nom, description, prix, nbr_place),new Cite(nom_c,nbr_p));          
            }}
            return matchingTypes;
        }*/
////afficher bel id mtaaa3 elogement staa3meltha lel pop up bch ne5ou m3aha logement
/*public HashMap<Logement,Cite> Affiche_complet() throws SQLException {
            HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            {
                 String req2 = "SELECT `id` ,logement.nom, `description`, `prix`, `nbr_place`,cite.nom, `num_villa` FROM `logement` inner join cite where logement.id_cite=cite.id_cite ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String description = rs.getString(3);
                int prix = rs.getInt(4);
                int nbr_place = rs.getInt(5);           
                String nom_c=rs.getString(6);               
                int nbr_p=rs.getInt(7);
                matchingTypes.put(new Logement(id,nom, description, prix, nbr_place),new Cite(nom_c,nbr_p));          
            }}
            return matchingTypes;
        }*/
public static HashMap<Logement,Cite> Affiche_complet() throws SQLException {
    Connection cnx = MyDBcon.getInstance().getCon();       
    HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            {
                 String req2 = "SELECT `id` ,logement.nom, `description`, `prix`, `nbr_place`,cite.nom, `num_villa` FROM `logement` inner join cite where logement.id_cite=cite.id_cite ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                String description = rs.getString(3);
                int prix = rs.getInt(4);
                int nbr_place = rs.getInt(5);           
                String nom_c=rs.getString(6);               
                int nbr_p=rs.getInt(7);
                matchingTypes.put(new Logement(id,nom, description, prix, nbr_place,nom_c),new Cite(nom_c,nbr_p));          
            }}
            return matchingTypes;
        }


///////////////////affichage des logement + cite + user pour admin //////////////////

public static HashMap<Logement,Cite> Affiche_admin() throws SQLException {
            HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            Connection cnx = MyDBcon.getInstance().getCon();
            {
                 String req2 = "SELECT  logement.nom, `description`, `prix` ,`nbr_place`,cite.nom,`username`,logement.id FROM `logement`,`cite`,`users` WHERE logement.id_user=users.id and logement.id_cite=cite.id_cite  ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {
                String nom = rs.getString(1);
                String description = rs.getString(2);
                int prix = rs.getInt(3);
                int nbr_place = rs.getInt(4);
                String nom_c=rs.getString(5);  
                String username = rs.getString(6);              
                int id = rs.getInt(7);
                matchingTypes.put(new Logement(id,nom, description, prix, nbr_place,nom_c,username),new Cite(nom_c,nbr_place));          
            }}
            return matchingTypes;
        }



  
  //////////////////// afiichaggeeeee des logements seulement des clients//////////////////////////////
  
  public static HashMap<Logement,Cite> Afficheuser(int id) throws SQLException {
      
            HashMap<Logement,Cite> matchingTypes = new HashMap<>();
            Connection cnx = MyDBcon.getInstance().getCon();
            {
                 String req2 = "SELECT logement.nom, `description`, `prix`, `nbr_place`,cite.nom, `num_villa`,logement.id FROM `logement` inner join cite where (logement.id_cite=cite.id_cite and logement.id_user='"+id+"') ";
                 PreparedStatement pstm2 = cnx.prepareStatement(req2); 
                  ResultSet rs= pstm2.executeQuery(req2);       
                 while (rs.next() )
                 {     
                String nom = rs.getString(1);
                String description = rs.getString(2);
                int prix = rs.getInt(3);
                int nbr_place = rs.getInt(4);           
                String nom_c=rs.getString(5);               
                int nbr_p=rs.getInt(6);
                int id_log=rs.getInt(7);
                matchingTypes.put(new Logement(id_log,nom, description, prix, nbr_place),new Cite(nom_c,nbr_p));          
            }}
            return matchingTypes;
        }
  

  
  public static void Updatelog(String nom,String description,int prix,int nbr_place,String id,String nomc,int idcite) {

        try {
            Connection cnx = MyDBcon.getInstance().getCon();        
            String sql = "Update logement set logement.nom=?, logement.description=? ,logement.prix=? ,logement.nbr_place=?  where logement.nom=?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);                   
            pstmt.setString(1, nom);        
            pstmt.setString(2, description);
            pstmt.setInt(3, prix);
            pstmt.setInt(4, nbr_place);  
            pstmt.setString(5,id);           
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
        
        
    }

         public static void Supprimer_log (int id) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();           
            String sql = "DELETE FROM logement WHERE id =?";
            PreparedStatement pstm = cnx.prepareStatement(sql); 
            pstm.setInt(1,id);
            pstm.executeUpdate();
            }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }  
}






