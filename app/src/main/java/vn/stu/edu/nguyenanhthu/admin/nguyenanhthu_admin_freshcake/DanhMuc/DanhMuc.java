package vn.stu.edu.nguyenanhthu.admin.nguyenanhthu_admin_freshcake.DanhMuc;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    private int id_danhmuc;
    private String tendanhmuc;

    public DanhMuc(){
    }

    public DanhMuc(int id_danhmuc, String tendanhmuc){
        this.id_danhmuc = id_danhmuc;
        this.tendanhmuc = tendanhmuc;
    }

    public int getId_danhmuc(){
        return id_danhmuc;
    }

    public void setId_danhmuc(int id_danhmuc){
        this.id_danhmuc = id_danhmuc;
    }

    public String getTendanhmuc(){
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc){
        this.tendanhmuc = tendanhmuc;
    }

    @Override
    public String toString(){
        return tendanhmuc ;
    }
}
