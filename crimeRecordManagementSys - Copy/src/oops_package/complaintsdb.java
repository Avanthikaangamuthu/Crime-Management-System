package oops_package;

import java.sql.ResultSet;
import java.util.ArrayList;

public class complaintsdb{

    boolean dbInsert(complaints complaintObj){
        boolean isInsert = false;
        try {
            connection obj = new connection();
            isInsert = obj.stmt.execute("INSERT INTO complaints(u_email,cmp_stmt,cmp_sts,type_of_incident,location,date_time,phn_no) VALUES('"+complaintObj.complaintantEmailId+"','"+complaintObj.stmt+"','inprogress','"+complaintObj.typeOfIncident+"','"+complaintObj.locationOfCrime+"','"+complaintObj.dateTime+"','"+complaintObj.phnNo+"')");
        } catch (Exception e) {
            System.out.println("Some unknown error occurred\n");
        }
        return !isInsert;
    }

    boolean dbSetStmt(String cmpStmt, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET cmp_stmt = '"+cmpStmt+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetCmpSts(String cmpSts, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET cmp_sts = '"+cmpSts+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetTypeOfIncident(String typeOfIncident, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET type_of_incident = '"+typeOfIncident+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetLocationOfCrime(String location, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET location = '"+location+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetDateTime(String dateTime, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET  date_time = '"+ dateTime+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbSetPhnNo(String phnNo, int cmpId){
        boolean isUpdate = false;
        try{
            connection obj = new connection();
            isUpdate = obj.stmt.execute("UPDATE complaints SET phnNo = '"+phnNo+"' WHERE cmp_id = '"+cmpId+"'");
        }catch(Exception e){
            System.out.println("Some unknown error occurred");
        }
        return !isUpdate;
    }

    boolean dbDelete(int cmpId){
        boolean isDelete = false;
        try {
            connection obj = new connection();
            isDelete = obj.stmt.execute("DELETE FROM complaints WHERE cmp_id = '"+cmpId+"'");
        } catch (Exception e) {
            System.out.println("Some unknown error occurred");
        }
        return !isDelete;
    }

    ArrayList<complaints> dbDisplay(){
        ArrayList<complaints> complaint = new ArrayList<>();
        try {
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM complaints");
            while(rs.next()){
                String dateTime = rs.getTimestamp("date_time").toString();
                complaints curObj = new complaints(rs.getInt("cmp_id"),rs.getString("u_email"),rs.getString("cmp_stmt"),rs.getString("cmp_sts"),rs.getString("type_of_incident"),rs.getString("location"),dateTime,rs.getString("phn_no"));
                complaint.add(curObj);
            }
        } catch (Exception e) {
            System.out.println("Some unknown error occurred\n");
        }
        return complaint;
    }

    ArrayList<complaints> dbDisplay(String email){
        ArrayList<complaints> complaint = new ArrayList<>();
        try {
            connection obj = new connection();
            ResultSet rs = obj.stmt.executeQuery("SELECT * FROM complaints WHERE u_email ='"+email+"'");
            while(rs.next()){
                String dateTime = rs.getTimestamp("date_time").toString();
                complaints curObj = new complaints(rs.getInt("cmp_id"),rs.getString("u_email"),rs.getString("cmp_stmt"),rs.getString("cmp_sts"),rs.getString("type_of_incident"),rs.getString("location"),dateTime,rs.getString("phn_no"));
                complaint.add(curObj);
            }
        } catch (Exception e) {
            System.out.println("Some unknown error occurred\n");
        }
        return complaint;
    }
}

