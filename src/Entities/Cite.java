/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Entities;
package Entities;

/**
 *
 * @author DongFeng
 */
public class Cite {
    
    //////////////////////////////////////////////// enntitieeeesss ///////////////////////////////////////
    
    private int id_cite ; 
    private String nom ;
    private int num_villa;

    public Cite() {
    }
    
    public Cite(String nom,int num_villa) {
        this.nom = nom;
        this.num_villa = num_villa;
    }
   
    public Cite(int id_cite,String nom,int num_villa) {
        this.id_cite=id_cite ;
        this.nom=nom ; 
        this.num_villa=num_villa;
    }

    ///////////////////////////////////////////getteeerssssss//////////////////////////
    
    public int getId_cite() {
        return id_cite;
    }

    public String getNom() {
        return nom;
    }
    
     public int getNum() {
        return num_villa;
    }


    //////////////////////////////////////setterrrrrrrrsssss ////////////////////////////////////////
   
       public void setId_cite(int id_cite) {
        this.id_cite = id_cite;
    }
     
    public void setNom(String nom) {
        this.nom = nom;
    }
    
        public void setNum_villa(int num_villa) {
        this.num_villa = num_villa;
    }
        
      
//////////////////////////////// tooo striiiiiinnnnngg//////////////////////////////////////////////////
    @Override
    public String toString() {
        return "Cite{"  + "nom=" + nom + ", num_villa=" + num_villa + '}';
    }    
    
}
