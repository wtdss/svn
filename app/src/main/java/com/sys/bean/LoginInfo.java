package com.sys.bean;

import java.io.Serializable;

public class LoginInfo implements Serializable {

    private String account;

    private String password;

    private Boolean passwordRemember;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordRemember() {
        return passwordRemember;
    }

    public void setPasswordRemember(Boolean passwordRemember) {
        this.passwordRemember = passwordRemember;
    }
}
