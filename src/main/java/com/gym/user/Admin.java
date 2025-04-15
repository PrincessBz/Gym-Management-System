package com.gym.user;


public class Admin extends User {

    public Admin() {
           this.role = "admin";
    }


   public Admin(int id, String username, String password, String email , String phoneNumber, String address) {
         super(id, username, email, password, phoneNumber, address, "admin");
   }

}