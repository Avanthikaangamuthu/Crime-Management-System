package oops_package;

import java.util.Scanner;

abstract class member {
    protected String emailId;
    protected String pass;
    protected String name;
    protected String phnNo;
    protected int age;

    static Scanner sc = new Scanner(System.in);

    member(){}

    member(String emailId, String pass, String name, String phnNo, int age){
        this.emailId = emailId;
        this.pass = pass;
        this.name = name;
        this.phnNo = phnNo;
        this.age = age;
    }

    member(String emailId, String name, String phnNo, int age){
        this.emailId = emailId;
        this.name = name;
        this.phnNo = phnNo;
        this.age = age;
    }

    abstract void actions(String email_id) throws Exception;
}
