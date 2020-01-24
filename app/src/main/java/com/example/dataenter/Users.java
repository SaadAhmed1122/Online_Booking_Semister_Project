package com.example.dataenter;

public class Users {
    private String UserName;
    private String CompanyName;
    private String phone_no;
    private String Address;

    public Users() {
    }

    public Users(String userName, String companyName,String phone_no,String address) {
        UserName = userName;
        CompanyName = companyName;
        this.phone_no = phone_no;
        Address = address;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUserName() {
        return UserName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getAddress() {
        return Address;
    }
}
