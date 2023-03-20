package oops_package;

import java.util.ArrayList;
import java.util.Scanner;

public class complaints {
    protected int cmpId;
    protected String complaintantEmailId;
    protected String stmt;
    protected String cmpSts;
    protected String typeOfIncident;
    protected String locationOfCrime;
    protected String dateTime;
    protected String phnNo;

    complaints(){}

    complaints(String complaintantEmailId, String stmt, String cmpSts, String typeOfIncident, String locationOfCrime, String dateTime, String phnNo){
        this.complaintantEmailId = complaintantEmailId;
        this.stmt = stmt;
        this.cmpSts = cmpSts;
        this.typeOfIncident = typeOfIncident;
        this.locationOfCrime = locationOfCrime;
        this.dateTime = dateTime;
        this.phnNo = phnNo;
    }

    complaints(int cmpId, String complaintantEmailId, String stmt, String cmpSts, String typeOfIncident, String locationOfCrime, String dateTime, String phnNo){
        this.cmpId = cmpId;
        this.complaintantEmailId = complaintantEmailId;
        this.stmt = stmt;
        this.cmpSts = cmpSts;
        this.typeOfIncident = typeOfIncident;
        this.locationOfCrime = locationOfCrime;
        this.dateTime = dateTime;
        this.phnNo = phnNo;
    }

    static Scanner sc = new Scanner(System.in);
    
    void setStmt(String cmpStmt, int cmpId){
        complaintsdb dbObj = new complaintsdb();
        if(dbObj.dbSetStmt(cmpStmt, cmpId))
            System.out.println("*********Updation Successfull*********");
        else
            System.out.println("Some unknown error occurred");
    }

    void setTypeOfIncident(String typeOfIncident, int cmpId){
        complaintsdb dbObj = new complaintsdb();
        if(dbObj.dbSetTypeOfIncident(typeOfIncident, cmpId))
            System.out.println("*********Updation Successfull*********");
        else
            System.out.println("Some unknown error occurred");
    }

    void setDateTime(String dateTime, int cmpId){
        if(dateTime.length()<15)
            System.out.println("Date & Time must be of the format YYYY:MM:DD HH:MM:SS");
        else{
            complaintsdb dbObj = new complaintsdb();
            if(dbObj.dbSetDateTime(dateTime, cmpId))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occurred");
        }
    }

    void setCmpSts(String cmpSts, int cmpId){
        if(!(cmpSts.equalsIgnoreCase("Inprogress") || cmpSts.equalsIgnoreCase("closed")))
            System.out.println("Invalid Status");
        else{
            complaintsdb dbObj = new complaintsdb();
            if(dbObj.dbSetCmpSts(cmpSts, cmpId))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void setLocationOfCrime(String location, int cmpId){
        complaintsdb dbObj = new complaintsdb();
        if(dbObj.dbSetLocationOfCrime(location, cmpId))
            System.out.println("*********Updation Successfull*********");
        else
            System.out.println("Some unknown error occured");
    }

    void setPhnNo(String phnNo, int cmpId){
        String temp = phnNo.replaceAll("[^0-9]", "");
        if(phnNo.length()<10 || temp.length()!=phnNo.length())
            System.out.println("Invalid Mobile Number");
        else {
            complaintsdb dbObj = new complaintsdb();
            if(dbObj.dbSetPhnNo(phnNo, cmpId))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void delete(int cmpId){
        complaintsdb dbObj = new complaintsdb();
        if(dbObj.dbDelete(cmpId))
            System.out.println("Deletion Successfull\n");
        else
            System.out.println("Some unknown error occurred\n");
    }

    void display(){
        complaintsdb dbObj = new complaintsdb();
        ArrayList<complaints> complaint = dbObj.dbDisplay();
        String format = "%1$-5s|%2$-23s|%3$-35s|%4$-10s|%5$-25s|%6$-15s|%7$-25s|%8$-15s\n";
        System.out.println();
        System.out.format(format, "ID","Email","Complaint Statement","Status","Type Of Incident","Location","Date & Time","Phone Number");
        System.out.println();
        for(complaints complaintObj : complaint)
            System.out.format(format,complaintObj.cmpId, complaintObj.complaintantEmailId, complaintObj.stmt, complaintObj.cmpSts, complaintObj.typeOfIncident, complaintObj.locationOfCrime, complaintObj.dateTime, complaintObj.phnNo);
    }

    //method-overloading
    void display(String email){
        complaintsdb dbObj = new complaintsdb();
        ArrayList<complaints> complaint = dbObj.dbDisplay();
        String format = "%1$-5s|%2$-23s|%3$-35s|%4$-10s|%5$-25s|%6$-15s|%7$-25s|%8$-15s\n";
        System.out.println();
        System.out.format(format, "ID","Email","Complaint Statement","Status","Type Of Incident","Location","Date & Time","Phone Number");
        System.out.println();
        for(complaints complaintObj : complaint)
            System.out.format(format,complaintObj.cmpId, complaintObj.complaintantEmailId, complaintObj.stmt, complaintObj.cmpSts, complaintObj.typeOfIncident, complaintObj.locationOfCrime, complaintObj.dateTime, complaintObj.phnNo);
    }

    void addComplaint(String email) throws Exception{        
        System.out.print("Enter Complaint Statement: ");
        String stmt = sc.nextLine();

        System.out.print("Enter Type of Incident: ");
        String typeOfIncident = sc.nextLine();

        System.out.print("Enter Date & Time (Fromat - YYYY-MM-DD HH:MM:SS): ");
        String dataTime = sc.nextLine();

        System.out.print("Enter Location: ");
        String location = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phnNo = sc.nextLine();

        complaints complaintObj = new complaints(email, stmt, typeOfIncident, dataTime, location, location, phnNo);

        complaintsdb complaintsDbObj = new complaintsdb();

        if(complaintsDbObj.dbInsert(complaintObj))
            System.out.println("Records Inserted Successfully");
        else
            System.out.println("Some unknown error occured");
    }

    void updateCredentials(int cmp_id) throws Exception{
        String doOperation = "yes";
        do{
            System.out.print("\n1.Update Statement\n2.Update type of incident\n3.Update Location\n4.Update date&time\n5.Update Complaint Status\n6.Update Phone number\nEnter Choice:    ");
            int choice = Integer.parseInt(sc.nextLine()); 
            switch(choice){
                case 1:
                    String stmt = sc.nextLine();
                    setStmt(stmt, cmp_id);
                    break;
                case 2:
                    String type_of_incident = sc.nextLine();
                    setTypeOfIncident(type_of_incident, cmp_id);
                    break;
                case 3:
                    String location = sc.nextLine();
                    setLocationOfCrime(location, cmp_id);
                    break;
                case 4:
                    String date_time = sc.nextLine();
                    setDateTime(date_time,cmp_id);
                    break;
                case 5:
                    String cmp_sts = sc.nextLine();
                    setCmpSts(cmp_sts,cmp_id);
                    break;
                case 6:
                    String phnNo = sc.nextLine();
                    setPhnNo(phnNo,cmpId);
                    break;
                default:
                    break;
            }
            System.out.print("Any other updations to be performed ? YES : NO ");
            doOperation = sc.nextLine();
        }while(doOperation.equalsIgnoreCase("yes"));
    }

    void modifyComplaint(String email) throws Exception{
        int choice = 0;
        do{
            System.out.print("\n1.View Complaint\n2.View All Complaints\n3.Add Complaint\n4.Update Complaint\n5.Delete Complaint\n6.Exit\n7.Logout\nEnter Choice:    ");
            choice = Integer.parseInt(sc.nextLine());
            int id = 0;
            String email_id = "";
            switch(choice){
                case 1:
                    System.out.print("Enter Complainant email-id: ");
                    email_id = sc.nextLine();
                    display(email_id);
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    System.out.print("Enter Complainant email-id: ");
                    email_id = sc.nextLine();
                    addComplaint(email_id);
                    break;
                case 4:
                    System.out.print("Enter Complaint ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    updateCredentials(id);
                    break;
                case 5:
                    System.out.print("Enter Complaint ID: ");
                    id = Integer.parseInt(sc.nextLine());
                    delete(id);
                    break;
                case 6:
                    System.out.println("*********Exit from complaints module is successfull*********");
                    break;
                case 7:
                    userdb userDbObj = new userdb();
                    userDbObj.setLogoutTime(email);
                    System.out.println("*********Logout is successfull*********");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(choice<=5);
    }
}
