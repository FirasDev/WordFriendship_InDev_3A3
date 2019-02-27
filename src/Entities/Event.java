package Entities;



import java.util.Objects;


public class Event {

    private int Id;
    private String nom;
    private String type;
    private String lieu;
    private String pays;
    private String descr;
    private String date_debut;
    private String date_fin;
    private int Num_villa;
    private int nbr_place;
    private float frais;
    private int Id_user;
    
    private String picture;

    public Event() {
    }
  
    
   

    public Event(String nom, String type, String lieu, int Num_villa, String pays, String date_debut, String date_fin, String descr,int nbr_place,float frais,int Id_user) {
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.Num_villa = Num_villa;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_place = nbr_place;
        this.frais = frais;
        this.Id_user=Id_user;
    }

    public Event(int Id, String nom, String type, String lieu, String pays, String descr, String date_debut, String date_fin, int Num_villa, int nbr_place, float frais, int Id_user) {
        this.Id = Id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Num_villa = Num_villa;
        this.nbr_place = nbr_place;
        this.frais = frais;
        this.Id_user = Id_user;
    }
    
  

   
       public Event(String nom, String type, String lieu,  int Num_villa,String pays, String date_debut, String date_fin, String descr, int nbr_place, float frais, int Id_user, String picture) {
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Num_villa = Num_villa;
        this.nbr_place = nbr_place;
        this.frais = frais;
        this.Id_user = Id_user;
        this.picture=picture;
    }
      
    
   
    
    public Event(String nom, String type, String lieu, int Num_villa, String pays, String date_debut, String date_fin, String descr,int nbr_place,float frais) {
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.Num_villa = Num_villa;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_place = nbr_place;
        this.frais = frais;
      
       
    }

    public Event(int Id, String nom, String type, String lieu,int Num_villa, String pays,  String date_debut, String date_fin,String descr,  int nbr_place, float frais) {
        this.Id = Id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Num_villa = Num_villa;
        this.nbr_place = nbr_place;
        this.frais = frais;
    }

     public Event(int Id, String nom, String type, String lieu,int Num_villa, String pays,  String date_debut, String date_fin,String descr,  int nbr_place, float frais,int Id_user) {
        this.Id = Id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Num_villa = Num_villa;
        this.nbr_place = nbr_place;
        this.frais = frais;
        this.Id_user=Id_user;
    }
          public Event(int Id,String nom, String type, String lieu,int Num_villa, String pays,  String date_debut, String date_fin,String descr,  int nbr_place, float frais,int Id_user,String picture) {
        this.Id = Id;
        this.nom = nom;
        this.type = type;
        this.lieu = lieu;
        this.pays = pays;
        this.descr = descr;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.Num_villa = Num_villa;
        this.nbr_place = nbr_place;
        this.frais = frais;
        this.Id_user=Id_user;
        this.picture=picture;
    }


      //////////////////////////////////////// Getters //////////////////////////////////////////////////
    public int getId() {
        return Id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getLieu() {
        return lieu;
    }

    public String getPays() {
        return pays;
    }

    public String getDescr() {
        return descr;
    }

   

    public int getNum_villa() {
        return Num_villa;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public float getFrais() {
        return frais;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getId_user() {
        return Id_user;
    }

   

    public String getPicture() {
        return picture;
    }

  
    

   
    

   
    

  

    //////////////////////////////////////// Setters //////////////////////////////////////////////////
    public void setId(int Id) {
        this.Id = Id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


    public void setNum_villa(int Num_villa) {
        this.Num_villa = Num_villa;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public void setFrais(float frais) {
        this.frais = frais;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    
    public void setPicture(String picture) {
        this.picture = picture;
    }




  

    
  

  
  

      //////////////////////////////////////// Other //////////////////////////////////////////////////
  


   

 
  

   /* @Override
    public String toString() {
        return "Event{" + "nom=" + nom + ", type=" + type + ", lieu=" + lieu + ", pays=" + pays + ", descr=" + descr + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", Num_villa=" + Num_villa + ", nbr_place=" + nbr_place + ", frais=" + frais + '}';
    }
*/
    
    
    @Override
    public String toString() {
        return "Event{" + "Id=" + Id + ", nom=" + nom + ", type=" + type + ", lieu=" + lieu + ", pays=" + pays + ", descr=" + descr + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", Num_villa=" + Num_villa + ", nbr_place=" + nbr_place + ", frais=" + frais + ", Id_user=" + Id_user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.Id;
        hash = 59 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.pays, other.pays)) {
            return false;
        }
        if (!Objects.equals(this.descr, other.descr)) {
            return false;
        }
        if (!Objects.equals(this.date_debut, other.date_debut)) {
            return false;
        }
        if (!Objects.equals(this.date_fin, other.date_fin)) {
            return false;
        }
        return true;
    }
}
