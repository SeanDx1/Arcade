package com.example.seanactivity;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdminClass extends UserClass{

    private static AdminClass admin = null;

    public AdminClass(String full_name, String email,String dob, String phone, String gender) {
        super(full_name,email,dob,phone,gender);

    }
    public static AdminClass getInstance(){
        if(admin == null){
            admin = new AdminClass("Sean","seanifrah1@gmail.com","15/07/1998","052-8721111","Male");
             DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
             databaseReference.setValue(admin);
        }
        return admin;
    }

    public void ResetUserScore(int userScore)
    {

    }

}