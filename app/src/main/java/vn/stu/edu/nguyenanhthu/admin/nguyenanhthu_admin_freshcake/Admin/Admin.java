package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.Admin;

import java.io.Serializable;

public class Admin implements Serializable {
    private int id_admin;
    private String username;
    private String password;

    public Admin(){
    }

    public Admin(int id_admin, String username, String password){
        this.id_admin = id_admin;
        this.username = username;
        this.password = password;
    }

    public int getId_admin(){
        return id_admin;
    }

    public void setId_admin(int id_admin){
        this.id_admin = id_admin;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String toString(){
        return  id_admin +
                "-" + username +
                "-" + password ;
    }
}
