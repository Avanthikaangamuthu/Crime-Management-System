package oops_package;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class officer extends member{
    protected int stationId;
    protected String officerRole;

    static Scanner sc = new Scanner(System.in);

    officer(){}

    officer(String emailId, String pass, String name, String phnNo, int age, int stationId, String officerRole){
        super(emailId, pass, name, phnNo, age);
        this.stationId = stationId;
        this.officerRole = officerRole;
    }

    officer(String emailId, String name, String phnNo, int age, int stationId, String officerRole){
        super(emailId, name, phnNo, age);
        this.stationId = stationId;
        this.officerRole = officerRole;
    }

    //setters
    void setName(String name, String email){
        if(!Pattern.matches("[a-zA-Z]+",name))
            System.out.println("Invalid Name");
        else{
            officerdb officerDbObj = new officerdb();
            if(officerDbObj.dbSetName(name, email))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured"); 
        }
    }

    void setAge(int age, String email){
        if(age<10)
            System.out.println("Invalid Age");
        else{
            officerdb dbObj = new officerdb();
            if(dbObj.dbSetAge(age, emailId))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void setPhnNo(String phnNo, String email){
        String temp = phnNo.replaceAll("[^0-9]", "");
        if(phnNo.length()<10 || temp.length()!=phnNo.length())
            System.out.println("Invalid Mobile Number");
        else {
            officerdb dbObj = new officerdb();
            if(dbObj.dbSetPhnNo(phnNo, email))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void setPosition(String position, String email){
        if(!Pattern.matches("[a-zA-Z0-9]+",position))
            System.out.println("Invalid Position");
        else{
            officerdb dbObj = new officerdb();
            if(dbObj.dbSetPosition(position, email))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void setStationID(int station_id, String email){
        if(station_id<1)
            System.out.println("Station ID cannot be negative");
        else { 
            officerdb dbObj = new officerdb();
            if(dbObj.dbSetStationId(stationId, email))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void addOfficer(){        
        System.out.print("Enter officer Name: ");
        String name = sc.nextLine();

        System.out.print("Enter officer Email-id: ");
        String email = sc.nextLine();

        System.out.print("Enter Officer password: ");
        String pass = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phnNo = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Station-id: ");
        int stationId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter officer Position: ");
        String officerRole = sc.nextLine();

        officer curObj = new officer(email, pass, name, phnNo, age, stationId, officerRole);

        officerdb officerDbObj = new officerdb();

        if(officerDbObj.dbAddOfficer(curObj))
            System.out.println("Records Inserted Successfully");
        else
            System.out.println("Some unknown error occured");
    }

    void delete(String email){
        officerdb dbObj = new officerdb();
        if(dbObj.dbDelete(emailId))
                System.out.println("*********Deletion Successfull*********");
            else
                System.out.println("Some unknown error occured");
    }

    void display(){
        officerdb dbObj = new officerdb();
        ArrayList<officer> officers = dbObj.dbDisplay();
        String format = "%1$-30s|%2$-15s|%3$-20s|%4$-10s|%5$-20s|%6$-15s\n";
        System.out.println();
        System.out.format(format, "Email-id","Name","Phone Number","Age","Station ID","Officer Role");
        System.out.println();
        for(officer officerObj : officers){
            System.out.format(format, officerObj.emailId, officerObj.name, officerObj.phnNo, officerObj.age, officerObj.stationId, officerObj.officerRole);
        }
    }  

    //method overriding
    void display(String email_id) throws Exception{
        officerdb dbObj = new officerdb();
        officer officerObj = dbObj.dbDisplay(email_id);
        if(officerObj==null){
            System.out.println("Invalid EmailId");
        }else{
            String format = "%1$-30s|%2$-15s|%3$-20s|%4$-10s|%5$-20s|%6$-15s\n";
            System.out.println();
            System.out.format(format, "Email-id","Name","Phone Number","Age","Station ID","Officer Role");
            System.out.println();
            System.out.format(format, officerObj.emailId, officerObj.name, officerObj.phnNo, officerObj.age, officerObj.stationId, officerObj.officerRole);
        }
    }  
    
    void updateCredentials(String email) throws Exception{
        String doOperation = "yes";
        do{
            System.out.print("\n1.Update Name\n2Update Age\n3.Update Phone Number\n4.Update Position\n5.Update Station ID\nEnter Choice:    ");
            int choice = Integer.parseInt(sc.nextLine());
            switch(choice){
                case 1:
                    String name = sc.nextLine();
                    setName(name,email);
                    break;
                case 2:
                    int age= Integer.parseInt(sc.nextLine());
                    setAge(age, email);
                    break;
                case 3:
                    String phnNo = sc.nextLine();
                    setPhnNo(phnNo, email);
                    break;
                case 4:
                    String position = sc.nextLine();
                    setPosition(position, email);
                    break;
                case 5:
                    int s_id = Integer.parseInt(sc.nextLine());
                    setStationID(s_id, email);
                    break;
                default:
                    break;
            }
            System.out.print("Any other updations to be performed ? YES : NO ");
            doOperation = sc.nextLine();
        }while(doOperation.equalsIgnoreCase("yes"));
    }

    void modifyOfficer(String email) throws Exception{
        int choice = 0;
        do{
            System.out.print("\n1.View Officer\n2.View All Officers\n3.Add Officer\n4.Update Officer Credentials\n5.Delete Officer\n6.Exit\n7.Logout\nEnter choice: ");
            choice = Integer.parseInt(sc.nextLine());
            String of_email = "";
            switch(choice){
                case 1:
                    System.out.print("Enter officer email-id: ");
                    of_email = sc.nextLine();
                    display(of_email);
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    addOfficer();
                    break;
                case 4:
                    System.out.print("Enter officer email-id: ");
                    of_email = sc.nextLine();
                    updateCredentials(of_email);
                    break;
                case 5:
                    System.out.print("Enter officer email-id: ");
                    of_email = sc.nextLine();
                    delete(of_email);
                    break;
                case 6:
                    System.out.println("*********Exit from officer module is successfull*********\n");
                    break;
                case 7:
                    officerdb officerDbObj = new officerdb();
                    officerDbObj.setLogoutTime(email);
                    System.out.println("*********Logout is successfull*********");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(choice<=5);
    }

    void actions(String email) throws Exception{
        int choice = 0;
        officerdb officerDbObj = new officerdb();
        if(officerDbObj.checkForRequests(email))
            officerDbObj.disPlayRequest(email);
        do{
            System.out.print("\n1.View Credentials\n2.Update Credentials\n3.Modify Complaint\n4.Modify user\n5.Logout\nEnter choice: ");
            choice = Integer.parseInt(sc.nextLine());
            complaints c = new complaints();
            user u = new user();
            switch(choice){
                case 1:
                    display(email);
                    break;
                case 2:
                    updateCredentials(email);
                    break;
                case 3: 
                    c.modifyComplaint(email);
                    break;
                case 4:
                    u.modifyUser(email);
                    break;
                case 5:
                    officerDbObj = new officerdb();
                    officerDbObj.setLogoutTime(email);
                    System.out.println("Logout is successfull");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(choice<=4);
    }
}
