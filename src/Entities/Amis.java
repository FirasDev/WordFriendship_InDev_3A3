/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Jamila
 */
public class Amis {
    
    private int id_a;
    private int id_ue;
    private int id_ur;
    private int etat_a;
    
    
    public Amis(){}
    public Amis(int id_a, int id_ue, int id_ur, int etat_a)
    {
        this.id_a=id_a;
        this.id_ue=id_ue;
        this.id_ur=id_ur;
        this.etat_a=etat_a;
    }
    
    public Amis( int id_ue, int id_ur)
    {
        this.id_ue=id_ue;
        this.id_ur=id_ur;
    }
    
    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }
    
    public int getId_ue() {
        return id_ue;
    }

    public void setId_ue(int id_ue) {
        this.id_ue = id_ue;
    }
    
    public int getId_ur() {
        return id_ur;
    }

    public void setId_ur(int id_ur) {
        this.id_ur = id_ur;
    }
    
    public int getEtat_a() {
        return etat_a;
    }

    public void setEtat_a(int etat_a) {
        this.etat_a = etat_a;
    }
     @Override
    public String toString() {
        return "Amis{" + "id_a=" + id_a + ", id_ue=" + id_ue + ", id_ur=" + id_ur +", etat_a=" + etat_a + '}';
    }
}
