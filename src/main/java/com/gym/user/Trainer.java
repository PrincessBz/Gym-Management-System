package com.gym.user;

public class Trainer extends User{
    public Trainer() {
        this.role = "Trainer";
    }
    public Trainer(int id, String username, String password, String email , String phoneNumber, String address) {
        super(id, username, password, email, phoneNumber, address, "Trainer");
    }
}