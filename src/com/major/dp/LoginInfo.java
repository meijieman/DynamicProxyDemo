package com.major.dp;

public class LoginInfo {

    private String usrname;
    private String pwd;

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "usrname='" + usrname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
