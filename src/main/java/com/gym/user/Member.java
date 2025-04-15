package com.gym.user;

public class Member extends User {
    public Member() {
        this.role = "Member";
    }
    public Member(int id, String username, String password, String email , String phoneNumber, String address) {
        super(id, username, password, email, phoneNumber, address, "Member");
    }

    
}
    