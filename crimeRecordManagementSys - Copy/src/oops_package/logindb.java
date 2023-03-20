package oops_package;

import java.sql.ResultSet;

public class logindb {
    String isValid(String emailId, String pass){
        String res = "";
        try{
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT role FROM login WHERE email = '"+emailId+"' AND pass = '"+pass+"'");
            if(rs.next())
                res = rs.getString("role");
        }catch(Exception e){
            System.out.println("Some unknown error occured");
        }
        return res;
    }
}
