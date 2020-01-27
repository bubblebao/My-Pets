package com.project.mypetsphuket.Model;

public class UserSelect {

    String nameCustomer , username ,phone , select;
    private Long rating;

    public UserSelect(){}

    public UserSelect(String nameCustomer, String username, String phone, String select, Long rating) {
        this.nameCustomer = nameCustomer;
        this.username = username;
        this.phone = phone;
        this.select = select;
        this.rating = rating;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
