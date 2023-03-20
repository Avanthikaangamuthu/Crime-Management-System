package oops_package;
import java.sql.*;

 public class connection {
	 protected Connection con;
	 protected Statement stmt;
	 connection() throws Exception{
		 con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/cms","root","Avanthi@9475");
		 stmt = con.createStatement();
	 }
 }
