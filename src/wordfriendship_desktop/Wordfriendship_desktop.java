/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordfriendship_desktop;

import Services.ExperienceCrud;
import java.sql.SQLException;

/**
 *
 * @author Firas
 */
public class Wordfriendship_desktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        ExperienceCrud.DisplayExperiences().stream().forEach(e->System.out.println(e));
        
    }
    
}
