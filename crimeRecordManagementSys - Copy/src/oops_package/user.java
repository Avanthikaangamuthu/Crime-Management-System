package oops_package;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class user extends member{

    protected int noOfComplaintsMade;

    user(){}

    user(String emailId, String pass, String name, String phnNo, int age, int noOfComplaintsMade){
        super(emailId, pass, name, phnNo, age);
        this.noOfComplaintsMade = noOfComplaintsMade;
    }

    user(String emailId, String name, String phnNo, int age, int noOfComplaintsMade){
        super(emailId, name, phnNo, age);
        this.noOfComplaintsMade = noOfComplaintsMade;
    }

    static Scanner sc = new Scanner(System.in);

    //setters
    void setName(String name, String emailId){
        if(!Pattern.matches("[a-zA-Z]+",name))
            System.out.println("Invalid Name");
        else{
            userdb dbObj = new userdb();
            if(dbObj.dbSetName(name, emailId))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void setAge(int age, String email){
        if(age<10)
            System.out.println("Invalid Age");
        else{
            userdb dbObj = new userdb();
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
            userdb dbObj = new userdb();
            if(dbObj.dbSetPhnNo(phnNo, email))
                System.out.println("*********Updation Successfull*********");
            else
                System.out.println("Some unknown error occured");
        }
    }

    void display(){
        userdb dbObj = new userdb();
        ArrayList<user> users = dbObj.dbDisplay();
        String format = "%1$-30s|%2$-15s|%3$-20s|%4$-10s|%5$-20s\n";
        System.out.println();
        System.out.format(format, "Email-id","Name","Phone Number","Age","No of complaints made");
        System.out.println();
        for(user userObj : users){
            System.out.format(format, userObj.emailId, userObj.name, userObj.phnNo, userObj.age, userObj.noOfComplaintsMade);
        }
    }

    //method overloading
    void display(String emailId){
        userdb dbObj = new userdb();
        user userObj = dbObj.dbDisplay(emailId);
        if(userObj==null){
            System.out.println("Invalid EmailId");
        }else{
            String format = "%1$-30s|%2$-15s|%3$-20s|%4$-10s|%5$-20s\n";
            System.out.println();
            System.out.format(format, "Email-id","Name","Phone Number","Age","No of complaints made");
            System.out.println();
            System.out.format(format, userObj.emailId, userObj.name, userObj.phnNo, userObj.age, userObj.noOfComplaintsMade);
        }
    }

    void addUser(){
        System.out.print("Enter Your Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Your Email-ID: ");
        String emailId = sc.nextLine();
        
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        System.out.print("Enter Phone number: ");
        String phnNo = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());

        userdb dbObj = new userdb();

        user userObj = new user(emailId, pass, name, phnNo, age, 0);

        dbObj.dbInsert(userObj);

    }

   void updateCredentials(String email_id){
        String doOperation = "yes";
        do{
            System.out.print("\n1.Update Name\n2.Update Phone-number\n3.Update Age\nEnter Choice:    ");
            int choice = Integer.parseInt(sc.nextLine()); 
            switch(choice){
                case 1:
                    String name = sc.nextLine();
                    setName(name, email_id);
                    break;
                case 2:
                    String phn_no = sc.nextLine();
                    setPhnNo(phn_no, email_id);
                    break;
                case 3:
                    int age = Integer.parseInt(sc.nextLine());
                    setAge(age, email_id);
                    break;
                default:
                    break;
            }
            System.out.print("Any other updations to be performed ? YES : NO ");
            doOperation = sc.nextLine();
        }while(doOperation.equalsIgnoreCase("yes"));
    }

    void makeRequest(String emailId){
        System.out.println("Enter Your Complaint Id : ");
        int complaintId = Integer.parseInt(sc.nextLine());

        System.out.println("Enter Request Message : ");
        String requestMsg = sc.nextLine();

        userdb userDbObj = new userdb();
        if(userDbObj.dbMakeRequest(emailId, complaintId, requestMsg))
            System.out.println("Request Sent Successfully");
    }

    void delete(String email) throws Exception{
        userdb dbObj = new userdb();
        if(dbObj.dbDelete(emailId))
            System.out.println("*********Deletion Successfull*********");
        else
            System.out.println("Some unknown error occured");
    }

    void actions(String email) throws Exception{
        int choice = 0;
        userdb userDbObj = new userdb();
        if(userDbObj.checkForRequests(email))
            userDbObj.disPlayRequest(email);
        do{
            System.out.print("\n1.View Credentials\n2.Update Credentials\n3.Add Complaint\n4.View Complaint Status\n5.Ask About Complaint Status\n6.Logout\nEnter Choice:    ");
            choice = Integer.parseInt(sc.nextLine());
            complaints c = new complaints();
            switch(choice){
                case 1:
                    display(email);
                    break;
                case 2:
                    updateCredentials(email);
                    break;
                case 3: 
                    c.addComplaint(email);
                    break;
                case 4:
                    c.display(email);
                    break;
                case 5:
                    makeRequest(email);
                    break;
                case 6:
                    userDbObj = new userdb();
                    userDbObj.setLogoutTime(email);
                    System.out.println("Logout is successfull");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(choice<=5);
    }

    void modifyUser(String email) throws Exception{
        int choice = 0;
        do{
            System.out.print("\n1.View User\n2.View All Users\n3.Add User\n4.Update User Credentials\n5.Delete a User\n6.Exit\n7.Logout\nEnter Choice:    ");
            choice = Integer.parseInt(sc.nextLine());
            String u_email = "";
            switch(choice){
                case 1:
                    System.out.print("Enter user email-id: ");
                    u_email = sc.nextLine();
                    display(u_email);
                    break;
                case 2:
                    display();
                    break;
                case 3:
                    addUser();
                    break;
                case 4: 
                    System.out.print("Enter user email-id: ");
                    u_email = sc.nextLine();
                    updateCredentials(u_email);
                    break;
                case 5:
                    System.out.print("Enter user email-id: ");
                    u_email = sc.nextLine();
                    delete(u_email);
                    break;
                case 6:
                    System.out.println("*********Exit from user module is successfull*********\n");
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
