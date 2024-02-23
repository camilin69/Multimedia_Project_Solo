package co.edu.uptc.controller;

import co.edu.uptc.model.Admin;
public class AdminController {
    private Admin admin = new Admin("Elon", "Musk", 1, "Elon1@uptc.admin.co", "1");

    
    public Admin getAdmin() {
        return admin;
    }
}