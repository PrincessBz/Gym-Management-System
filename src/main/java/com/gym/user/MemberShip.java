package com.gym.user;
public class MemberShip {
    private int membershipId;
    private String membershipType;
    private String membershipDescription;
    private double membershipcost;
    private int memberId; 

    public MemberShip() {
    }

    public MemberShip(int membershipId, String membershipType, String membershipDescription, double membershipcost, int memberId) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.membershipDescription = membershipDescription;
        this.membershipcost = membershipcost;
        this.memberId = memberId;
    }
    public int getMembershipId() {
        return membershipId;
    }
    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }
    public String getMembershipType() {
        return membershipType;
    }
    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
    public String getMembershipDescription() {
        return membershipDescription;
    }
    public void setMembershipDescription(String membershipDescription) {
        this.membershipDescription = membershipDescription;
    }
    public double getMembershipcost() {
        return membershipcost;
    }
    public void setMembershipcost(double membershipcost) {
        this.membershipcost = membershipcost;
    }
    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    @Override
    public String toString() {
        return "MemberShip{" +
                "membershipId=" + membershipId +
                ", membershipType='" + membershipType + '\'' +
                ", membershipDescription='" + membershipDescription + '\'' +
                ", membershipcost=" + membershipcost +
                ", memberId=" + memberId +
                '}';
    }
}
