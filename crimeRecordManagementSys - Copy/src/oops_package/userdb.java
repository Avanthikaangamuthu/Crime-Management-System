package oops_package;

import java.sql.ResultSet;
import java.util.ArrayList;

public class userdb{

    userdb(){}

    boolean dbInsert(user userObj){
        boolean isInsert1 = false, isInsert2 = false;
        try{
            connection obj = new connection();
            isInsert1 = obj.stmt.execute("INSERT INTO login VALUES('"+userObj.emailId+"','"+userObj.pass+"','user',current_timestamp())");
            isInsert2 = obj.stmt.execute("INSERT INTO user VALUES('"+userObj.emailId+"','"+userObj.name+"','"+userObj.phnNo+"','"+userObj.age+"',current_timestamp(),'"+userObj.noOfComplaintsMade+"')");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return (!isInsert1 && !isInsert2);
    }

    boolean dbSetName(String name, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE user SET u_name = '"+name+"'WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetAge(int age, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE user SET u_age = '"+age+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetPhnNo(String phnNo, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE user SET phn_no = '"+phnNo+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    ArrayList<user> dbDisplay(){
        ArrayList<user> users = new ArrayList<>();
        try{
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM user");
            while(rs.next()){
                user curObj = new user(rs.getString("e_mail"),rs.getString("u_name"),rs.getString("phn_no"),rs.getInt("u_age"),rs.getInt("noOfComplaintsMade"));
                users.add(curObj);
            }
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return users;
    }

    user dbDisplay(String emailId){
        try{
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM user WHERE e_mail ='"+emailId+"'");
            user curObj = new user();
            while(rs.next()){
                curObj = new user(rs.getString("e_mail"),rs.getString("u_name"),rs.getString("phn_no"),rs.getInt("u_age"),rs.getInt("noOfComplaintsMade"));
                return curObj;
            }
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return null;
    }

    boolean dbMakeRequest(String emailId, int complaintId, String requestMsg){
        boolean isInsert = false;
        try{
            connection obj = new connection();
            isInsert = obj.stmt.execute("INSERT INTO officerquery(complaintId, complaintantEmail, msg, requestTime) VALUES('"+complaintId+"','"+emailId+"','"+requestMsg+"',current_timestamp)");
        }catch(Exception e){
            System.out.println("Some unknown error occurred"+e);
        }
        return !isInsert;
    }

    boolean dbDelete(String emailId){
        boolean isDelete1 = false, isDelete2 = false;
        try{
            connection obj = new connection();
            isDelete1 = obj.stmt.execute("DELETE FROM login WHERE email = '"+emailId+"'");
            isDelete2 = obj.stmt.execute("DELETE FROM user WHERE e_mail = '"+emailId+"'");
        } catch (Exception e) {
            System.out.println("Some unknown error occurred");
        }
        return (!isDelete1 && !isDelete2);
    }

    void disPlayRequest(String emailId){
        try{
            connection obj = new connection();
            ResultSet rs1 = obj.stmt.executeQuery("SELECT logoutTime FROM login WHERE email = '"+emailId+"'");
            rs1.next();
            String dateTime = rs1.getTimestamp("logoutTime").toString();
            ResultSet rs2 = obj.stmt.executeQuery("SELECT * FROM userquery WHERE recTime > '"+dateTime+"'");
            while(rs2.next()){
                System.out.println("************************************************************************");
                System.out.println("\nComplaint Id : "+rs2.getInt("complaintId")+"\nComplaintant Email : "+rs2.getString("email")+"\nRequest Message : "+rs2.getString("msg"));
            }
            System.out.println("************************************************************************");
        }catch(Exception e){
            System.out.println("Some unknown error occurred"+e);
        }
    }

    boolean checkForRequests(String emailId){
        boolean isPresent = false;
        try{
            connection obj = new connection();
            ResultSet rs1 = obj.stmt.executeQuery("SELECT logoutTime FROM login WHERE email = '"+emailId+"'");
            String dateTime = "";
            if(rs1.next())
                dateTime = rs1.getTimestamp("logoutTime").toString();
            ResultSet rs2 = obj.stmt.executeQuery("SELECT COUNT(*) AS requestCount FROM userquery WHERE recTime > '"+dateTime+"'");
            rs2.next();
            int countOfRequests = rs2.getInt("requestCount");
            if(countOfRequests>=1)
                isPresent = true;
        }catch(Exception e){
            System.out.println("Some unkown error occurred"+e);
        }
        return isPresent;
    }

    void setLogoutTime(String email){
        try{
            connection obj = new connection();
            obj.stmt.execute("UPDATE login SET logoutTime = current_timestamp WHERE email = '"+email+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred"+e);
        }
    }
}
