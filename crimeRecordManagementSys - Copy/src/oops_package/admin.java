package oops_package;

import java.util.Scanner;

public class admin extends member{

    static Scanner sc = new Scanner(System.in);

    void actions(String email) throws Exception{
        int choice = 0;
        do{
            System.out.print("\n1.Modify Complaint\n2.Modify User\n3.Modify Officer\n4.Logout\nEnter Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            complaints c_obj = new complaints();
            user u_obj = new user();
            officer o_obj = new officer();
            switch(choice){
                case 1:
                    c_obj.modifyComplaint(email);
                    break;
                case 2:
                    u_obj.modifyUser(email);
                    break;
                case 3: 
                    o_obj.modifyOfficer(email);
                    break; 
                case 4:
                    userdb userDbObj = new userdb();
                    userDbObj.setLogoutTime(email);
                    System.out.println("*********Logout is successfull*********");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }while(choice<=3);
    }
}
