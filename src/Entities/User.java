/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.MyDBcon;
import java.sql.Timestamp;

/**
 *
 * @author Firas
 */
public class User {
	protected int id;
	protected String username;
	protected String email;
	protected Boolean enabled;
	protected String salt;
	protected String password;
	protected Timestamp last_login;
	protected String roles;
	protected String firstname;
	protected String lastname;
    
}
