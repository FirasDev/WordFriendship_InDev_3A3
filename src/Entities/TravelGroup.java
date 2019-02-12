/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Yefet
 */
public class TravelGroup {
    
    
    
    private int id ;
    private List<Travelbuddy> Members ;
    private String title ; 
    private String destination ;
    private Date date_debut ;

    public TravelGroup(String title, String destination, Date date_debut, Date date_fin, String plan) {
        this.title = title;
        this.destination = destination;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.plan = plan;
    }
    private Date date_fin ;
    private String plan ;

    public int getId() {
        return id;
    }

    public List<Travelbuddy> getMembers() {
        return Members;
    }

    public String getTitle() {
        return title;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getPlan() {
        return plan;
    }
    
    
    
    

}
