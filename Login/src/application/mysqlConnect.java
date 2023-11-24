package application;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class mysqlConnect {
	
	Connection con = null;

	public static Connection ConnectDb(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost: 3306/phonebook users", "root", "");
		 JOptionPane.showMessageDialog(null,"Connection made");
		 return con;
		} catch (Exception e) {
			 JOptionPane.showMessageDialog(null, e);
			 return null;
		}
		
	}

}
