/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Utils.MyDBcon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Firas
 */
public class PaysCrud {

    	public static TreeMap<String,Integer> GetNameIdMap() throws SQLException{
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		Connection con = MyDBcon.getInstance().getCon();
		String query = "SELECT id,name from pays order by id";
		try {
			PreparedStatement ste = con.prepareStatement(query);
			ResultSet set = ste.executeQuery();
			while (set.next()) {
				map.put(set.getString("name"), set.getInt("id"));
			}
			return map;
		} catch (SQLException ex) {
			Logger.getLogger(PaysCrud.class.getName()).log(Level.SEVERE, null, ex);

		}
		return null;
	}
    
}
