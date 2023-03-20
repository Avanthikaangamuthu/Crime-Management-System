package oops_package;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class officerdb{

    boolean dbAddOfficer(officer Officerobj){
        boolean isInsert1 = false, isInsert2 = false;
        try{
            connection obj = new connection();
            isInsert1 = obj.stmt.execute("INSERT INTO login VALUES('"+Officerobj.emailId+"','"+Officerobj.pass+"','officer',current_timestamp())");
            isInsert2 = obj.stmt.execute("INSERT INTO officer(e_mail,of_name,st_id,of_role,time,phnNo,age) VALUES('"+Officerobj.emailId+"','"+Officerobj.name+"','"+Officerobj.stationId+"','"+Officerobj.officerRole+"',current_timestamp()),'"+Officerobj.phnNo+"','"+Officerobj.age+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return (!isInsert1 && !isInsert2);
    }

    static Scanner sc = new Scanner(System.in);
    
    boolean dbSetName(String name, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE officer SET of_name = '"+name+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred\n");
        }
        return !isUpdate;
    }

    boolean dbSetAge(int age, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE officer SET age = '"+age+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetPhnNo(String phnNo, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE officer SET phnNo = '"+phnNo+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetStationId(int stationId, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE officer SET st_id = '"+stationId+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetPosition(String position, String emailId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE officer SET of_role = '"+position+"' WHERE e_mail = '"+emailId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbDelete(String emailId){
        boolean isDelete1 = false, isDelete2 = false;
        try{
            connection obj = new connection();
            isDelete1 = obj.stmt.execute("DELETE FROM login WHERE email = '"+emailId+"'");
            isDelete2 = obj.stmt.execute("DELETE FROM officer WHERE e_mail = '"+emailId+"'");
        } catch (Exception e) {
            System.out.println("Some unknown error occurred");
        }
        return (!isDelete1 && !isDelete2);
    }

    void respondFunction(String email, int complaintId, String respondMsg){
        try{
            connection obj = new connection();
            obj.stmt.execute("INSERT INTO userQuery(email, complaintId, msg, recTime) VALUES('"+email+"','"+complaintId+"','"+respondMsg+"',current_timestamp)");
        }catch(Exception e){
            System.out.println("Some unknown error occurred"+e);
        }
    }   

    void disPlayRequest(String emailId){
        try{
            connection obj = new connection();
            ResultSet rs1 = obj.stmt.executeQuery("SELECT logoutTime FROM login WHERE email = '"+emailId+"'");
            rs1.next();
            String dateTime = rs1.getTimestamp("logoutTime").toString();
            ResultSet rs2 = obj.stmt.executeQuery("SELECT * FROM officerquery WHERE requesttime > '"+dateTime+"'");
            while(rs2.next()){
                System.out.println("************************************************************************");
                System.out.println("Request id : "+rs2.getInt("queryId")+"\nComplaint Id : "+rs2.getInt("complaintId")+"\nComplaintant Email : "+rs2.getString("complaintantEmail")+"\nRequest Message : "+rs2.getString("msg"));
                System.out.println("Do you want to respond back ? 1 : View Next Record ? 2 : Exit ? 0");
                int choice = Integer.parseInt(sc.nextLine());
                if(choice==0)
                    return;
                else if(choice==1){
                    System.out.println("Enter respond message : ");
                    String respondmsg = sc.nextLine();
                    respondFunction(rs2.getString("complaintantEmail"), rs2.getInt("complaintId"), respondmsg);
                }
                else
                    continue;

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
            ResultSet rs2 = obj.stmt.executeQuery("SELECT COUNT(*) AS requestCount FROM officerquery WHERE requesttime > '"+dateTime+"'");
            rs2.next();
            int countOfRequests = rs2.getInt("requestCount");
            if(countOfRequests>=1)
                isPresent = true;
        }catch(Exception e){
            System.out.println("Some unkown error occurred"+e);
        }
        return isPresent;
    }

    ArrayList<officer> dbDisplay(){
        ArrayList<officer> officers = new ArrayList<>();
        try{
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM officer");
            while(rs.next()){
                officer curObj = new officer(rs.getString("e_mail"),rs.getString("of_name"),rs.getString("phnNo"),rs.getInt("age"),rs.getInt("st_id"),rs.getString("of_role"));
                officers.add(curObj);
            }
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return officers;
    }

    officer dbDisplay(String emailId){
        officer curObj = null;
        try{
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM officer WHERE e_mail = '"+emailId+"'");
            System.out.println();
            while(rs.next()){
                curObj = new officer(rs.getString("e_mail"),rs.getString("of_name"),rs.getString("phnNo"),rs.getInt("age"),rs.getInt("st_id"),rs.getString("of_role"));
            }
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return curObj;
    }

    void setLogoutTime(String email){
        try{
            connection obj = new connection();
            obj.stmt.execute("UPDATE login SET logoutTime = current_timestamp WHERE email = '"+email+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
    }

}
