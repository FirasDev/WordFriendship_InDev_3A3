
package Entities;


public class Album_Event {
    private int Id_event;
    private int Id_album;
    private String URL;

    public Album_Event( int Id_event, String URL) {
        this.Id_event = Id_event;
        this.URL = URL;
    }
    
   

    
       //////////////////////////////////////// Getters //////////////////////////////////////////////////
    public int getId_event() {
        return Id_event;
    }

    public int getId_album() {
        return Id_album;
    }

    public String getURL() {
        return URL;
    }
    
       //////////////////////////////////////// Setters //////////////////////////////////////////////////

    public void setId_event(int Id_event) {
        this.Id_event = Id_event;
    }

    public void setId_album(int Id_album) {
        this.Id_album = Id_album;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
     //////////////////////////////////////// Other //////////////////////////////////////////////////

    @Override
    public String toString() {
        return "Album_Event{" + "URL=" + URL + '}';
    }
    
}
