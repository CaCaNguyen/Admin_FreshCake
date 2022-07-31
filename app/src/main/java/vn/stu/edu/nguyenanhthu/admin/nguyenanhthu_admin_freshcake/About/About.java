package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.About;

import java.io.Serializable;

public class About implements Serializable {
    private String name_store;
    private String slogan;
    private String phone;
    private String address;
    private int since;

    public About(){
    }

    public About(String name_store, String slogan, String phone, String address, int since){
        this.name_store = name_store;
        this.slogan = slogan;
        this.phone = phone;
        this.address = address;
        this.since = since;
    }

    public String getName_store(){
        return name_store;
    }

    public void setName_store(String name_store){
        this.name_store = name_store;
    }

    public String getSlogan(){
        return slogan;
    }

    public void setSlogan(String slogan){
        this.slogan = slogan;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getSince(){
        return since;
    }

    public void setSince(int since){
        this.since = since;
    }

    @Override
    public String toString(){
        return name_store + '\'' +
                ", slogan='" + slogan + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", since=" + since +
                '}';
    }
}
