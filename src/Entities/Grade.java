/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDateTime;

/**
 *
 * @author Jamila
 */
public class Grade {
    private int id_g;
    private int id_ug;
    private String grade;

   
     public Grade() {
    }
     public Grade(int id_g,int id_ug,String grade,LocalDateTime date_g){
         this.id_g=id_g;
         this.id_ug=id_ug;
         this.grade=grade;
     
     }
     public Grade(int id_ug,String grade){
         this.id_ug=id_ug;
         this.grade=grade;
       
     }
     
      public Grade(String grade){
         this.grade=grade;
     }
    public void setId(int id_g) {
        this.id_g = id_g;
    }
    public int getId_ug() {
        return id_ug;
    }

    public void setId_ug(int id_ug) {
        this.id_ug = id_ug;
    }
             
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    } 
    @Override
    public String toString() {
        return "Grade{" + "grade=" + grade + '}';
    }
}             
         