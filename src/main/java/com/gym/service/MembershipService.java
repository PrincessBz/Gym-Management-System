package com.gym.service;

import java.sql.SQLException;
import java.util.List;

import com.gym.dao.MembershipDAO; 
import com.gym.user.MemberShip;

public class MembershipService {
    private final MembershipDAO membershipDAO = new MembershipDAO();

    public void purchasemembership(MemberShip membership, int memberId) throws SQLException {
              membership.setMemberId(memberId);
               membershipDAO.addMembership(membership);
        System.out.println("Membership purchased successfully!");
        }

        public void viewAllMemberships() throws SQLException {
           List<MemberShip> memberships = membershipDAO.getAllMemberships();
              if (memberships.isEmpty()) {
                System.out.println("No memberships available.");
              } else {
                System.out.println("Available memberships:");
                for (MemberShip membership : memberships) {
                     System.out.println(membership);
                }
                
              }

        }

        public void viewTotalRevenue() {
            double totalRevenue = membershipDAO.getTotalRevenue();
            System.out.println("Total revenue: $" + totalRevenue);
        }

   

   
}