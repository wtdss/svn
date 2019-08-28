package com.sys.bean;

import java.io.Serializable;

public class SignUpInfo implements Serializable {

    private String userJobNo;

    private String userPassword;

    public String getUserJobNo() {
        return userJobNo;
    }

    public void setUserJobNo(String userJobNo) {
        this.userJobNo = userJobNo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
