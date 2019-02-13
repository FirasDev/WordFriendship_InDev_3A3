
package Entities;


public class Reservation {
    
    private int Id_reserv;
    private int Id_event;
    private int Id_user;


    public Reservation(int Id_event, int Id_user) {
        this.Id_event = Id_event;
        this.Id_user = Id_user;
     
    }
    
    //////////////////////////////////////// Getters //////////////////////////////////////////////////

    public int getId_reserv() {
        return Id_reserv;
    }

    public int getId_event() {
        return Id_event;
    }

    public int getId_user() {
        return Id_user;
    }

 
    
    //////////////////////////////////////// Setters //////////////////////////////////////////////////

    public void setId_reserv(int Id_reserv) {
        this.Id_reserv = Id_reserv;
    }

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

 
  
    
    //////////////////////////////////////// Other //////////////////////////////////////////////////

   

    @Override
    public String toString() {
        return "Reservation{" + "Id_event=" + Id_event + ", Id_user=" + Id_user + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.Id_reserv;
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
        final Reservation other = (Reservation) obj;
        if (this.Id_reserv != other.Id_reserv) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
