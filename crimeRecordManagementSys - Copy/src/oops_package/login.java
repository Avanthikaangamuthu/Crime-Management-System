package oops_package;

import java.util.*;

public class login {
    static Scanner sc = new Scanner(System.in);

    login(){}

    void signIn() throws Exception{
        System.out.print("Enter Email-id: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        logindb logindbObj = new logindb();
        String res = logindbObj.isValid(email, pass);

        if(res.equalsIgnoreCase("user")){
            System.out.println("*********User login is successfully*********");
            member u_obj = new user();
            u_obj.actions(email);
        }
        else if(res.equalsIgnoreCase("officer")){
            System.out.println("*********officer login is successfully*********");
            member of_obj = new officer();
            of_obj.actions(email);
        }
        else if(res.equalsIgnoreCase("admin")){
            System.out.println("*********Admin login is successfully*********");
            member a_obj = new admin();
            a_obj.actions(email);
        }
        else{
            System.out.println("*********Invalid Credentials-Sign Up to Continue*********");
            actions();
        }
    }

    void signUp() throws Exception{
        user userObj = new user();
        userObj.addUser();
    }
    
    void actions() throws Exception{
        System.out.print("1.Sign In\n2.Sign Up\n3.Exit\nEnter choice : ");
        int choice = Integer.parseInt(sc.nextLine());
        switch(choice){
            case 1:
                signIn();
                break;
            case 2:
                signUp();
                break;
            case 3:
                System.out.println("\n*********Logout is successfull*********");
                break;
            default:
                break;
        }
    }
}
