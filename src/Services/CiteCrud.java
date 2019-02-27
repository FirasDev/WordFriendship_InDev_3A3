
package Services;

import Services.LogementCrud;
import Utils.MyDBcon;
import Entities.Cite;
import Entities.Logement;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * @author DongFeng
 */
public class CiteCrud {
    
    Connection cnx;
    
    public CiteCrud() throws SQLException {
        
       cnx = MyDBcon.getInstance().getCon();
          
    }
    
    
    
  ////////////////////////////////// AJOUTERRR CITE + LOGEEEEEEEEEMMMMMMMEEEEEEENT /////////////////////////////////////  
   public static void Ajouter_cite(Cite c,Logement l,int id_user) throws SQLException {
       Connection cnx = MyDBcon.getInstance().getCon();  
       try {
            LogementCrud lc = new LogementCrud();
             String req = "INSERT INTO `cite`(`nom`,`num_villa`) VALUES (?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, c.getNom());  
             pstm.setInt(2, c.getNum());  
             
            pstm.executeUpdate();
             
             String req1 = "SELECT `id_cite` FROM `cite` WHERE num_villa='"+c.getNum()+"' ";
             PreparedStatement pstm1 = cnx.prepareStatement(req1);
             ResultSet rs=pstm1.executeQuery();            
         if (rs.next())
         {
             int id= rs.getInt(1);
             System.out.println(id);
             lc.Ajouter_logement(l,id,id_user) ;
         }                         
         } catch (SQLException ex) {
             Logger.getLogger(CiteCrud.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
     
    
    ////////////////////////// suppressionnn cite + logeeemeennnnnt ///////////////
    public static void Supprimer_cite (int id_cite) {
        try {
            Connection cnx = MyDBcon.getInstance().getCon();           
            String sql = "DELETE FROM cite WHERE id_cite =?";
            PreparedStatement pstm = cnx.prepareStatement(sql); 
            pstm.setInt(1,id_cite);
            pstm.executeUpdate();
            }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        } 
       
    ////////////////////// modifierrrrr citeeee /////////////////////
    public static void Modifier_cite (int num_villa,String nom,int id_cite) throws SQLException {
            Connection cnx = MyDBcon.getInstance().getCon(); 
        try {
            String sql = "UPDATE cite SET `nom`=? ,`num_villa`=? WHERE id_cite=? ";
            PreparedStatement pstmt = cnx.prepareStatement(sql);

            pstmt.setString(1, nom);
            pstmt.setInt(2, num_villa);
            pstmt.setInt(3, id_cite);
           
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    ////////////////////////afficheeer ciiteeee ////////////////////////
    
    public static ArrayList<Cite> Afficher_cite () throws SQLException {
        Connection cnx = MyDBcon.getInstance().getCon(); 
        ArrayList<Cite> retour = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String req = "SELECT `nom`, `num_villa` FROM cite ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {

            String nom = resultat.getString(1);
            int num_villa = resultat.getInt(2);           
            retour.add(new Cite(nom,num_villa));
        }
        return retour;
    }
    
     public static ArrayList<Cite> Afficher_cite2 (int id) throws SQLException { //////fonction traja3 el logement mtaaa3 e cite hethyka pour le pop uup
        ArrayList<Cite> retour = new ArrayList<>();
        Connection cnx=MyDBcon.getInstance().getCon();
        Statement stm = cnx.createStatement();
        String req = "SELECT cite.nom, `num_villa` FROM `cite` INNER JOIN logement WHERE  (logement.id_cite=cite.id_cite AND logement.id='" + id+"') ";
        ResultSet resultat = stm.executeQuery(req);
        while (resultat.next()) {
            String nom = resultat.getString(1);
            int num_villa = resultat.getInt(2);           
            retour.add(new Cite(nom,num_villa));
        }
        return retour;
    }
   
    
    ///////////////////////rechercheee Id_citeeeee ////////////////////////////////////
    
    public static int SearchPays(String pays) throws SQLException {
        Connection con = MyDBcon.getInstance().getCon();
        String query = "SELECT id_cite from `cite` WHERE `nom` ='" + pays+"'";
        try{           
        Statement ste = con.createStatement();
        ResultSet result = ste.executeQuery(query);
      if (result.next()){
        return result.getInt("id_cite");}       
        } catch (SQLException ex) {
            Logger.getLogger(Cite.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    

    ///////////////////////////////////////fonction de recherche citeeeessss selon nom du pays /////////////////////////////////
       
    public static List<Cite> RechercherSelonPays(String pays) throws SQLException {
     
        List<Cite> cites = new ArrayList<Cite>();
        Connection con = MyDBcon.getInstance().getCon();
        Statement ste = con.createStatement();
        int idpays = SearchPays(pays);
        if (idpays == 0) {
           return null ;
        }else {
        String query = "SELECT * from `cite`  WHERE id_cite= " + idpays;
        try{
            
        ResultSet set = ste.executeQuery(query);
        while (set.next()) {
            Cite cit = new Cite (set.getString("nom"),set.getInt("num_villa"));
            cites.add(cit);
            return cites;
        }
        return cites;
               
        } catch (SQLException ex) {
            Logger.getLogger(Cite.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
        }
    }

    
    //////////////// veriiiiiiiif citeeeee redandenceee ///////////////////////////////////////////////////
    
    public static boolean VerifierCite(Cite ci ,String pays) throws SQLException{ 
        
        long x = RechercherSelonPays(pays).stream().count();  
          System.out.println(x);
        if (x>0) 
        {            
            return true;             
        }   
          return false;    
    }       
}

